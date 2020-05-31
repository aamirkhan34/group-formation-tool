package com.group1.SDCapplication.signup.controller;

import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.services.UserSignup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @GetMapping("/signup")
    public String userSignup(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("mailExists", false);
        return "Signup/Signup";
    }

    @PostMapping("/signup")
    public String userSignup(@ModelAttribute User user, Model model){
        UserSignup userSignup = new UserSignup();
        String response = userSignup.addNewUser(user);
        if(response == "Signup Successful")
        {
            return "Home/index";
        }
        else {
            String mailExist = "Mail already exists";
            model.addAttribute("mailExists", mailExist);
            model.addAttribute("response", response);
            return "Signup/Signup";
        }

    }

}
