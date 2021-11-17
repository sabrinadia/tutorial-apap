package apap.tutorial.emsidi.service;
import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.CabangDb;
import apap.tutorial.emsidi.repository.PegawaiDb;
import apap.tutorial.emsidi.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PegawaiRestServiceImpl implements PegawaiRestService{
    private final WebClient webClient;

    @Autowired
    private PegawaiDb pegawaiDb;
    @Autowired
    private CabangDb cabangDb;

    @Override
    public PegawaiModel createPegawai(PegawaiModel pegawai) {
        return pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> retrieveListPegawai() {
        return pegawaiDb.findAll();
    }

    @Override
    public PegawaiModel getPegawaiByNoPegawai(Long noPegawai) {
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);
        if(pegawai.isPresent()){
            return pegawai.get();
        }
        else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate) {
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        pegawai.setNamaPegawai(pegawaiUpdate.getNamaPegawai());
        pegawai.setJenisKelamin(pegawaiUpdate.getJenisKelamin());
        pegawai.setCabang(pegawaiUpdate.getCabang());
        return pegawaiDb.save(pegawai);
    }

    @Override
    public void deletePegawai(Long noPegawai) {
        LocalTime now = LocalTime.now();
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        CabangModel cabang = pegawai.getCabang();

        if((now.isBefore(cabang.getWaktuBuka()) || now.isAfter(cabang.getWaktuTutup()))){
            pegawaiDb.delete(pegawai);
        }
        else{
            throw new UnsupportedOperationException();
        }

    }

    public PegawaiRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.pegawaiUrl).build();
    }

    @Override
    public Mono<String> getStatus(Long noPegawai){
        return this.webClient.get().uri("/rest/pegawai/"+ noPegawai+ "/status")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public PegawaiModel updateAge(Long noPegawai, PegawaiModel pegawaiUpdate) {
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        CabangModel cabang = pegawai.getCabang();
        LocalTime now = LocalTime.now();
        if(now.isBefore(cabang.getWaktuBuka()) || now.isAfter(cabang.getWaktuTutup())) {
            final String uri = "https://api.agify.io/?name=" + String.valueOf(pegawai.getNamaPegawai().split(" ")[0]);
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            Integer umur = Integer.parseInt(result.split(",")[1].substring(6, result.split(",")[1].length()));
            pegawai.setUmur(umur);
            pegawaiDb.save(pegawai);
        } else {
            throw new UnsupportedOperationException("Cabang still open");
        }
        return pegawaiDb.findByNoPegawai(noPegawai).get();
    }

}
