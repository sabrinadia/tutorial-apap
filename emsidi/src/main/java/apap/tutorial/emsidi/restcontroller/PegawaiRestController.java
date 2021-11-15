package apap.tutorial.emsidi.restcontroller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangRestService;
import apap.tutorial.emsidi.service.PegawaiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")

public class PegawaiRestController {
    @Autowired
    private PegawaiRestService pegawaiRestService;

    @PostMapping(value="/pegawai")
    private PegawaiModel createPegawai(@Valid @RequestBody PegawaiModel pegawai, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else{
            return pegawaiRestService.createPegawai(pegawai);
        }
    }

    @GetMapping(value="/pegawai/{noPegawai}")
    private PegawaiModel retrievePegawai(@PathVariable("noPegawai") Long noPegawai){
        try{
            return pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Pegawai " + String.valueOf(noPegawai) + " Not Found."
            );
        }
    }

    @DeleteMapping(value="/pegawai/{noPegawai}")
    private ResponseEntity deletePegawai(@PathVariable("noPegawai") Long noPegawai){
        try{
            pegawaiRestService.deletePegawai(noPegawai);
            return ResponseEntity.ok("Pegawai with ID " + String.valueOf(noPegawai) + " Deleted!");

        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai) + " Not Found!"
            );
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cabang is still open"
            );
        }
    }

    @PutMapping(value="/pegawai/{noPegawai}")
    private PegawaiModel updatePegawai(@PathVariable("noPegawai") Long noPegawai, @RequestBody PegawaiModel pegawai){
        try{
            return pegawaiRestService.updatePegawai(noPegawai, pegawai);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai) + " Not Found!"
            );
        }
    }

    @GetMapping(value= "/list-pegawai")
    private List<PegawaiModel> retrieveListPegawai() {
        return pegawaiRestService.retrieveListPegawai();
    }

//    @GetMapping(value="/pegawai/umur/{noPegawai}")
//    private PegawaiModel AgePredictionPagawai(@PathVariable("noPegawai") Long noPegawai){
//        try{
//            pegawaiRestService.predictAge(noPegawai);
//            PegawaiModel pegawai = pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
//            return pegawai;
//        } catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Pegawai with no pegawai " + String.valueOf(noPegawai) + " Not Found."
//            );
//        }
//    }

    @GetMapping(value = "/pegawai/umur/{noPegawai}")
    private PegawaiModel agePredict(
            @PathVariable("noPegawai") Long noPegawai
    ) {
        PegawaiModel pegawai = pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        try {
            return pegawaiRestService.updateAge(noPegawai,pegawai);
        }
        catch(UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cabang is still open!"
            );
        }
    }


    @GetMapping(value="/listpegawai/{genderPegawai}")
    private List<PegawaiModel> retrievePegawai(@PathVariable("genderPegawai") int genderPegawai){
        try{
            return pegawaiRestService.retrieveListPegawaiByGender(genderPegawai);
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai dengan gender bervalue " + String.valueOf(genderPegawai) + " Not Found."
            );
        }
    }

}
