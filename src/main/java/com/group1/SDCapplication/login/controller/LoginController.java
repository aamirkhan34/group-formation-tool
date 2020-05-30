package com.group1.SDCapplication.login.controller;

import com.group1.SDCapplication.login.models.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String Login(Model model){
        model.addAttribute("loginError", false);
        model.addAttribute("userCredentials", new UserCredentials());
        return "/Login/Login";
    }

}
