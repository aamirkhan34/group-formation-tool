package com.group1.SDCapplication.login.controller;

import com.group1.SDCapplication.login.dao.UserPasswordReset;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.models.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/user/passwordreset")
    public ModelAndView passwordRest(@RequestParam(value = "token") String token, @ModelAttribute User user, Model model){
        String useremail = jwtTokenUtil.getUsernameFromToken(token);
        boolean isTokenValid = jwtTokenUtil.isTokenExpired(token);
        user.setEmail(useremail);
        if(!isTokenValid){
            model.addAttribute("userResetPassword", user);
            modelAndView.setViewName("Login/ResetPassword/ResetPassword");
            return modelAndView;
        }
        else {
            modelAndView.setViewName("/Home/error");
            return modelAndView;
        }
    }
    @PostMapping("/user/passwordreset")
    public ModelAndView passwordReset(@ModelAttribute User user, Model model, @ModelAttribute String email){
        UserPasswordReset userPasswordReset = new UserPasswordReset();
        model.addAttribute("userResetPassword", user);
        String userEmail = user.getEmail();
        String password = user.getPassword();
        System.out.println(userEmail);
        System.out.println(password);
        boolean result = userPasswordReset.passwordReset(userEmail, password);
        System.out.println(result);
        if(result){
            modelAndView.setViewName("/Home/index");
        }
        else {
            modelAndView.setViewName("/Home/error");
        }
        return modelAndView;
    }
}
