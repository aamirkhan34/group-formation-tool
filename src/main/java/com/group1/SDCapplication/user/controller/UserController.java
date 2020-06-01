package com.group1.SDCapplication.user.controller;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.services.UserValidation;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.user.services.GuestUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private String Token;
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @PostMapping("/home")
    public String userHome(@ModelAttribute UserCredentials userCredentials, Model model){
        model.addAttribute("userCredentials", userCredentials);
        List<String> userRoles = new ArrayList<>();
        String finalRole = null;
        UserValidation userValidation = new UserValidation();
        boolean isUserValid = userValidation.userValidation(userCredentials);
        if(isUserValid){
            Token = userValidation.generateToken(userCredentials);
            userRoles = userValidation.getUserRoles(userCredentials);
        for (String role: userRoles) {
            finalRole = role;
        }
            model.addAttribute("token", Token);
            model.addAttribute("role", finalRole);
            if(finalRole.equals("student") && !jwtTokenUtil.isTokenExpired(Token))
            {
                return "/User/Student";
            }
            if(finalRole.equals("guest") && !jwtTokenUtil.isTokenExpired(Token))
            {
                GuestUser guestUser = new GuestUser();
                List<Courses> coursesForGuest = new ArrayList<>();
                coursesForGuest = guestUser.getCourses();
                model.addAttribute("courses", coursesForGuest);
                return "/User/Guest";
            }
            else if(finalRole.equals("instructor") && !jwtTokenUtil.isTokenExpired(Token))
            {
                return "/User/Instructor";
            }
            else {
                return "/Home/index";
            }
        }
        else {
            String loginError = "Invalid Credentials";
            model.addAttribute("loginError", loginError);
            return "/Login/Login";
        }
    }

    @GetMapping("/logout")
    public String userLoggedout(){
        return "Home/index";
    }

    @PostMapping("/logout")
    public String userLogout(Model model){
        String logouttoken = (String) model.getAttribute("token");
        model.addAttribute("token", Token);
        System.out.println("logout function called");
        System.out.println(logouttoken);
        return "/Home/index";
    }

}
