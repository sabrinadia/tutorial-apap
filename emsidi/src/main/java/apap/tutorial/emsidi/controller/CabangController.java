package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
@Controller
public class CabangController {
    @Qualifier("cabangServiceImpl") //ini cnya gede apa kecil
    @Autowired
    private  CabangService cabangService;
    private  PegawaiService pegawaiService;

    @GetMapping("/cabang/add")
    public String addCabangForm(Model model){
        model.addAttribute("cabang", new CabangModel());
        return "form-add-cabang";
    }

    @PostMapping("/cabang/add")
    public String addCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ){
        cabangService.addCabang(cabang);
        model.addAttribute("noCabang", cabang.getNoCabang());
        return "add-cabang";

    }

    @GetMapping("/cabang/viewall")
    public String listCabang(Model model){
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/view")
    public String viewDetailCabang(
            @RequestParam(value="noCabang") Long noCabang,
            Model model
    ){
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        List<PegawaiModel> listPegawai = cabang.getListPegawai();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listPegawai", listPegawai);

        return "view-cabang";
    }

    @GetMapping("/cabang/update/{noCabang}")
    public String updateCabangForm(
            @PathVariable long noCabang,
            Model model
    ){
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        model.addAttribute("cabang", cabang);
        return "form-update-cabang";
    }

    @PostMapping("/cabang/update")
    public String updateCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ){
        cabangService.updateCabang(cabang);
        model.addAttribute("noCabang", cabang.getNoCabang());
        return "update-cabang";

    }
//Tutorial
    @GetMapping("/cabang/CabangViewAll")
    public String cabangViewAll(Model model){
        List<CabangModel> listCabang = cabangService.getCabangList();
        listCabang.sort((c1, c2) -> c1.getNamaCabang().compareTo(c2.getNamaCabang()));
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/delete/{noCabang}")
    public String deleteCabang(
            @PathVariable Long noCabang,
            Model model){

        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);

        if(cabangService.deleteCabang(cabang) && cabangService.cekTutup(cabang)){
            return "delete-cabang";
        }
        else{
            return "delete-cabang-restriction";
        }




    }



    @GetMapping("/cabang/viewcabang")
    public String viewCabang(
            @RequestParam(value="noCabang") Long noCabang,
            Model model
    ){
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        List<PegawaiModel> listPegawai = cabang.getListPegawai();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listPegawai", listPegawai);

        return "view-cabang";
    }








}
