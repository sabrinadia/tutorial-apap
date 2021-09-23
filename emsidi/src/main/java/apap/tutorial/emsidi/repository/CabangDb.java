package apap.tutorial.emsidi.repository;

import apap.tutorial.emsidi.model.CabangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CabangDb extends JpaRepository<CabangModel, Long> {
    Optional<CabangModel> findByNoCabang(Long noCabang);
    //List<CabangModel> findByNamaCabangOrderByNamaCabang(String Nama);

}

