package apap.tutorial.emsidi.repository;
import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>  {
    Optional<PegawaiModel> findByNoPegawai(Long noPegawai);
//    Optional<PegawaiModel> deletePegawaiModelByNoPegawai(Long noPegawai);
}
