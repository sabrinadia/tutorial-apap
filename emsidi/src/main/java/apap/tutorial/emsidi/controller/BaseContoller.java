package apap.tutorial.emsidi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BaseContoller {

    @GetMapping("/")
    private String home(){
        return "home";
    }


}
