package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import java.time.*;


public interface PegawaiService {
    void addPegawai(PegawaiModel pegawai);
    void updatePegawai(PegawaiModel pegawai);
    PegawaiModel getPegawaiByNoPegawai(Long noPegawai);
    boolean cekTutup(CabangModel cabang);
    void deletePegawai(PegawaiModel pegawai);

}
