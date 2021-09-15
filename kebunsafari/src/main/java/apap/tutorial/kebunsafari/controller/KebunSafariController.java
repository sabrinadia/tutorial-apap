package apap.tutorial.kebunsafari.controller;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import apap.tutorial.kebunsafari.service.KebunSafariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class KebunSafariController {
    @Autowired
    private KebunSafariService kebunSafariService;

    @RequestMapping ("/kebun-safari/add")
    public String addKebunSafari(
            @RequestParam (value="id", required = true) String idKebunSafari,
            @RequestParam(value="nama", required = true) String namaKebunSafari,
            @RequestParam(value="alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            Model model
    ){
        //membuat objek Kebun Safari baru
        KebunSafariModel kebunSafari = new KebunSafariModel(idKebunSafari, namaKebunSafari, alamat, noTelepon);

        //memanggil service addKebunSafari
        kebunSafariService.addKebunSafari(kebunSafari);

        //Menambahkan variabel kebunSafari untuk dirender di ThymeLeaf
        model.addAttribute("kebunSafari", kebunSafari);

        //Mereturn template html yang dipakai
        return "add-kebun-safari";

    }

    @RequestMapping("/")
    public String listKebunSafari(Model model){
        //Mendapatkan list seluruh objek Kebun Safari
        List<KebunSafariModel> listKebunSafari = kebunSafariService.getKebunSafariList();

        //Menambahkan list untuk dirender di ThymeLeaf
        model.addAttribute("listKebunSafari", listKebunSafari);

        //Mereturn template html yang dipakai
        return "get-all-kebun-safari";

    }

    @RequestMapping("/kebun-safari")
    public String getKebunSafariById(@RequestParam(value="id", required = true) String idKebunSafari, Model model){
//        System.out.println("INI IDNYAAAA");
//        System.out.println(idKebunSafari);

        if(kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari) != null) {
            //Mendapatkan objek dari kebun safari
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);
            //System.out.println(kebunSafari.getNamaKebunSafari());
            //Menambahkan objek untuk dirender di Thymeleaf
            model.addAttribute("kebunSafari", kebunSafari);

            //Mereturn template html yang dipakai
            return "detail-kebun-safari";
        }
        else{
            return "id-not-found";
        }

    }

    @RequestMapping("/kebun-safari/view/{id}")
    public String cariKebun(@PathVariable("id") String id, Model model){
        //mengecek apakah id yang dimasukkan ada di list
        if(kebunSafariService.getKebunSafariByIdKebunSafari(id) != null){
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(id);

            model.addAttribute("kebunSafari", kebunSafari);

            return "detail-kebun-safari";
        }
        //jika id tidak ditemukan, render halaman id not found
        else{
            return "id-not-found";
        }
    }

    @RequestMapping("/kebun-safari/update/{id}")
    public String updateNoTelpKebunSafari(
            @PathVariable("id") String id,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            Model model){
        if(kebunSafariService.getKebunSafariByIdKebunSafari(id) != null) {
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(id);
            kebunSafari.setNoTelepon(noTelepon);
            model.addAttribute("kebunSafari", kebunSafari);

            return "update-noTelepon";

        }
        else{
            return "id-not-found";
        }
    }


    @RequestMapping("/kebun-safari/delete/{id}")
    public String deleteKebunSafari(
            @PathVariable("id") String id,
            Model model){
        if(kebunSafariService.getKebunSafariByIdKebunSafari(id) != null) {
            KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(id);
            KebunSafariModel deletedKebunSafari = kebunSafariService.deleteKebunSafariByIdKebunSafari(kebunSafari);

            model.addAttribute("kebunSafari", kebunSafari);

            return "delete-kebun-safari";

        }
        else{
            return "id-not-found";
        }
    }







}
