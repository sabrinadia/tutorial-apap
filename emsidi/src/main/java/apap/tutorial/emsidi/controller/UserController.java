package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.*;
import apap.tutorial.emsidi.service.UserService;
import apap.tutorial.emsidi.service.RoleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";

    }

    @PostMapping(value="/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        String email = user.getEmail();
        String message2 = "";
        String result = "";
        if(userService.checkEmail(email)) {
            if (userService.checkPassword(user.getPassword())) {
                userService.addUser(user);
                model.addAttribute("user", user);
                result= "redirect:/";
            } else {
                message2 = "password invalid";
                result = "form-add-user";
            }
        }
        else{
            message2 = "email invalid";
            result = "form-add-user";
        }
        model.addAttribute("message", message2);
        return result;
    }

    @GetMapping("/viewall")
    public String viewAllUser(
            Model model
    ){
        model.addAttribute("listUser", userService.getListUser());
        return "viewall-user";
    }


    @GetMapping("/delete/{username}")
    public String deleteUser(
            @PathVariable String username,
            Model model
    ){
        UserModel user = userService.getUserById(username);
            userService.deleteUser(user);
            model.addAttribute("user", user);
            return "redirect:/user/viewall";
    }

    @GetMapping("/update/password")
    public String updatePassword(
    ){
        return "update-password";
    }

    @PostMapping("/update/password")
    public String SubmitPassword(
            Model model,
            @ModelAttribute PasswordModel pw
            ){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        UserModel user = userService.getUserById(SecurityContextHolder.getContext().getAuthentication().getName());
        String message1 = "";
        String result = "";
        if(pw.getConfirmPassword().equals(pw.getNewPassword())){
            if(bcrypt.matches(pw.getPrevPassword(), user.getPassword())){ //ngga cocok sama yang di database
                if(userService.checkPassword(pw.getNewPassword())){
                    userService.updatePassword(user, pw.getNewPassword() );
                    result ="redirect:/";
                }
                else{
                    message1 = "password tidak sesuai";
                    result ="update-password";
                }

            }
            else{
                message1 = "password invalid";
                result ="update-password";
            }
        } else{
            message1 = "password tidak sama";
            result ="update-password";
        }

        model.addAttribute("message", message1);
        return result;
    }

    //new password sama kaya previous
    //validasi password sama kaya database atau ngga


}
