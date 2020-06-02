package com.group1.SDCapplication.login.controller;

import com.group1.SDCapplication.login.dao.UserPasswordResetDao;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/user/passwordreset")
    public String passwordRest(@RequestParam(value = "token") String token, @ModelAttribute User user, Model model){
        String userEmail = jwtTokenUtil.getUsernameFromToken(token);
        boolean isTokenValid = jwtTokenUtil.isTokenExpired(token);
        model.addAttribute("tokenError", false);
        user.setEmail(userEmail);
        if(!isTokenValid){
            model.addAttribute("userResetPassword", user);
            return "/Login/ResetPassword/ResetPassword";
//            modelAndView.setViewName("Login/ResetPassword/ResetPassword");
//            return modelAndView;
        }
        else {
            model.addAttribute("tokenError", true);
            return "/Home/Error";
//            modelAndView.setViewName("/Home/Error");
//            return modelAndView;
        }
    }
    @PostMapping("/user/passwordreset")
    public String  passwordReset(@ModelAttribute User user, Model model, @ModelAttribute String email){
        UserPasswordResetDao userPasswordResetDao = new UserPasswordResetDao();
        model.addAttribute("userResetPassword", user);
        model.addAttribute("tokenError", false);
        String userEmail = user.getEmail();
        String password = user.getPassword();
        System.out.println(userEmail);
        System.out.println(password);
        boolean result = userPasswordResetDao.passwordReset(userEmail, password);
        System.out.println(result);
        if(result){
            return "/Home/index";
//            modelAndView.setViewName("Home/Index");
        }
        else {
            String error = "Invalid Token";
            model.addAttribute("tokenError", error);
            return "/Home/Error";
//            modelAndView.setViewName("/Home/Error");
        }
    }
}
