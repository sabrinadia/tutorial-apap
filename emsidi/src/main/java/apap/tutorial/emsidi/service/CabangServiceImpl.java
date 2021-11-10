package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class CabangServiceImpl implements CabangService {
    @Autowired
    CabangDb cabangDb;

    @Override
    public void addCabang(CabangModel cabang){


        cabangDb.save(cabang);

    }

    @Override
    public void updateCabang(CabangModel cabang){
        cabangDb.save(cabang);
    }

    @Override
    public List<CabangModel> getCabangList(){
        return cabangDb.findAll();
    }

    @Override
    public CabangModel getCabangByNoCabang(Long noCabang){
        Optional<CabangModel> cabang = cabangDb.findByNoCabang(noCabang);
        if(cabang.isPresent()){
            return cabang.get();
        }
        return null;
    }

    @Override
    public boolean deleteCabang(CabangModel cabang) {
        if(cabang.getListPegawai().size() == 0){
            cabangDb.delete(cabang);
            return true;
        }
        return false;
    }

    @Override
    public boolean cekTutup(CabangModel cabang){
        LocalTime buka = cabang.getWaktuBuka();
        LocalTime tutup = cabang.getWaktuTutup();
        LocalTime sekarang = LocalTime.now();

        int value1 = sekarang.compareTo(buka);
        int value2 = sekarang.compareTo(tutup);

        if( value1< 0 && value2 < 0  ){
            return true;
        }
        else if(value1 >0 && value2 >0 && buka.compareTo(tutup) < 0){
            return true;
        }
        else if( value1< 0 && value2 > 0 && buka.compareTo(tutup) >0  ){
            return true;
        }

        return false;



    }


}
