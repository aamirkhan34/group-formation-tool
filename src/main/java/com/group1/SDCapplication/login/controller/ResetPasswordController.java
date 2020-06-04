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
            return "resetPassword";
        }
        else {
            model.addAttribute("tokenError", true);
            return "error";
        }
    }
    @PostMapping("/user/passwordreset")
    public String  passwordReset(@ModelAttribute User user, Model model, @ModelAttribute String email){
        UserPasswordResetDao userPasswordResetDao = new UserPasswordResetDao();
        model.addAttribute("userResetPassword", user);
        model.addAttribute("tokenError", false);
        String userEmail = user.getEmail();
        String password = user.getPassword();
        boolean result = userPasswordResetDao.passwordReset(userEmail, password);

        if(result){
            return "index";
        }
        else {
            String error = "Invalid Token";
            model.addAttribute("tokenError", error);
            return "error";
        }
    }
}
