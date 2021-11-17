package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.time.*;

@Service
@Transactional

public class PegawaiServiceImpl implements  PegawaiService {
    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public void addPegawai(PegawaiModel pegawai){

        pegawaiDb.save(pegawai);


    }

    @Override
    public PegawaiModel getPegawaiByNoPegawai(Long noPegawai){
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);
        if(pegawai.isPresent()){
            return pegawai.get();
        }
        return null;
    }

    @Override
    public void updatePegawai(PegawaiModel pegawai){
        pegawaiDb.save(pegawai);
    }

    @Override
    public boolean cekTutup(CabangModel cabang){
        LocalTime buka = cabang.getWaktuBuka();
        LocalTime tutup = cabang.getWaktuTutup();
        LocalTime sekarang = LocalTime.now();

        int value1 = sekarang.compareTo(buka);
        int value2 = sekarang.compareTo(tutup);

        if( value1< 0 && value2 < 0  ){  //op 9 buka 1 buka 2
            return true;
        }
        else if(value1 >0 && value2 >0 && buka.compareTo(tutup) < 0){ // op 18 buka 17 tutup 9
            return true;
        }
        else if( value1< 0 && value2 > 0 && buka.compareTo(tutup) >0  ){
            return true;
        }

        return false;


    }

    @Override
    public void deletePegawai(PegawaiModel pegawai){
        pegawaiDb.delete(pegawai);
    }





}
