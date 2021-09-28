package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class PegawaiController {
    @Qualifier("pegawaiServiceImpl")
    @Autowired
    PegawaiService pegawaiService;

    @Qualifier("cabangServiceImpl")
    @Autowired
    CabangService cabangService;

    @GetMapping("/pegawai/add/{noCabang}")
    public String addPegawaiForm(
            @PathVariable Long noCabang,
            Model model
    ){
        PegawaiModel pegawai = new PegawaiModel();
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        pegawai.setCabang(cabang);
        model.addAttribute("noCabang", noCabang);
        model.addAttribute("pegawai", pegawai);
        return "form-add-pegawai";

    }

    @PostMapping("/pegawai/add")
    public String addPegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ){
        pegawaiService.addPegawai(pegawai);
        model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
        model.addAttribute("namaPegawai", pegawai.getNamaPegawai());
        return "add-pegawai";
    }

    //Tutorial

    @GetMapping("/pegawai/update/{noCabang}/{noPegawai}")
    public String updatePegawaiForm(
           @PathVariable Long noCabang,
           @PathVariable long noPegawai,
            Model model
    ){
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        PegawaiModel pegawai = pegawaiService.getPegawaiByNoPegawai(noPegawai);

        if(pegawaiService.cekTutup(cabang)){
            model.addAttribute("pegawai", pegawai);
            model.addAttribute("noCabang", noCabang);
            return "form-update-pegawai";

        }

        return "update-restriction";


    }

    @PostMapping("/pegawai/update")
    public String pegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ){
        pegawaiService.updatePegawai(pegawai);
        model.addAttribute("noPegawai", pegawai.getNoPegawai());
        return "update-pegawai";

    }

    @GetMapping("/pegawai/delete/{noCabang}/{noPegawai}")
    public String deletePegawai(
            @PathVariable Long noCabang,
            @PathVariable long noPegawai,
            Model model
    ){
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        PegawaiModel pegawai = pegawaiService.getPegawaiByNoPegawai(noPegawai);

        if(pegawaiService.cekTutup(cabang)) {
            pegawaiService.deletePegawai(pegawai);
            model.addAttribute("pegawai", pegawai);

            return "delete-pegawai";
        }

        return "delete-restriction";

    }






}
