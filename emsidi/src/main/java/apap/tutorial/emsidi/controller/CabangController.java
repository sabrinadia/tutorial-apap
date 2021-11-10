package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.PegawaiService;
import org.hibernate.boot.jaxb.spi.Binding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
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


    @PostMapping(value="/cabang/add", params="addRow")
    public String addRow(
            @ModelAttribute CabangModel cabang,
            BindingResult bindingResult,
            Model model
    ){
        if(cabang.getListMenu() == null){
            cabang.setListMenu(new ArrayList<MenuModel>());
        }
        cabang.getListMenu().add(new MenuModel());

        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @PostMapping(value="/cabang/add", params="removeRow")
    public String removeRow(
            @ModelAttribute CabangModel cabang,
            BindingResult bindingResult,
            final HttpServletRequest request,
            Model model
    ){
        final int index = Integer.valueOf(request.getParameter("removeRow"));
        cabang.getListMenu().remove(index);
        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @PostMapping(value="/cabang/add", params="save")
    public String submitAddMenu(
            @ModelAttribute CabangModel cabang,
            Model model
    ){
      CabangModel cabang1 = cabangService.getCabangByNoCabang(cabang.getNoCabang());
      model.addAttribute("jumlahMenu", cabang.getListMenu().size());
      for(MenuModel menu : cabang.getListMenu()){
          menu.getListCabang().add(cabang1);
      }
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












}
