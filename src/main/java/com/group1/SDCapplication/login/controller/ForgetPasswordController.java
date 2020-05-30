package com.group1.SDCapplication.login.controller;


import com.group1.SDCapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgetPasswordController {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/forgetpassword")
    public String Forget_Password( Model model){
        model.addAttribute("user", new User());
        return "/Login/ForgetPassword";
    }

    @PostMapping("/forgetpassword")
    public String Send_Email_with_password(@ModelAttribute User user, Model model)
    {
        model.addAttribute("user", user);
        String User_email = user.getEmail();
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(User_email);
        simpleMessage.setSubject("test1");
        simpleMessage.setText("test1");
        javaMailSender.send(simpleMessage);
        return "/Login/Login";
    }

}
