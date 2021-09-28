package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import apap.tutorial.emsidi.service.CabangService;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class BaseContoller {
//    @Qualifier("cabangServiceImpl") //ini cnya gede apa kecil
//    @Autowired
//    private  CabangService cabangService;

    @GetMapping("/")
    private String home(){
//        List<CabangModel> listCabang = cabangService.getCabangList();
//        model.addAttribute("listCabang", listCabang);

        return "home";
    }


}
