package com.group1.SDCapplication.user.controller;

import com.group1.SDCapplication.courseadministration.dao.InstructorCourseDao;
import com.group1.SDCapplication.courseadministration.dao.StudentCourseDao;
import com.group1.SDCapplication.courseadministration.dao.TaCourseDao;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.services.UserValidation;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.user.dao.Course;
import com.group1.SDCapplication.user.services.GuestUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private String token;
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @PostMapping("/home")
    public String userHome(@ModelAttribute UserCredentials userCredentials, Model model){
        model.addAttribute("userCredentials", userCredentials);
        List<String> userRoles = new ArrayList<>();
        String finalRole = null;
        UserValidation userValidation = new UserValidation();
        boolean isUserValid = userValidation.userValidation(userCredentials);
        if(isUserValid){
            User user = userValidation.getUserDetails(userCredentials);
            userRoles = userValidation.getUserRoles(userCredentials);
            token = userValidation.generateTokenWithRoles(user,userRoles);
            for (String role: userRoles) {
                finalRole = role;
                System.out.println(finalRole);
            }
            System.out.println(finalRole);
            model.addAttribute("token", token);
            model.addAttribute("role", finalRole);
            if(finalRole.equals("student") && !jwtTokenUtil.isTokenExpired(token))
            {
            	Course clist = new StudentCourseDao(user.getEmail());
    			model.addAttribute("courses", clist.getCourses());
                return "course-listing";
            }
            if(finalRole.equals("guest") && !jwtTokenUtil.isTokenExpired(token))
            {
                GuestUser guestUser = new GuestUser();
                List<Courses> coursesForGuest = new ArrayList<>();
                coursesForGuest = guestUser.getCourses();
                model.addAttribute("courses", coursesForGuest);
                return "guest";
            }
            else if((finalRole.equals("instructor") || finalRole.equals("ta")) && !jwtTokenUtil.isTokenExpired(token))
            {
            	if (finalRole.equals("instructor"))
    			{
        			Course clist = new InstructorCourseDao(user.getEmail());
        			model.addAttribute("courses", clist.getCourses());
    			} else {
    				Course clist = new TaCourseDao(user.getEmail());
        			model.addAttribute("courses", clist.getCourses());
    			}
                return "course-listing";
            }
            else if(finalRole.equals("admin") && !jwtTokenUtil.isTokenExpired(token))
            {
                return "admin";
            }
            else {
                return "index";
            }
        }
        else {
            String loginError = "Invalid Credentials";
            model.addAttribute("loginError", loginError);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String userLoggedout(){
        return "index";
    }

    @PostMapping("/logout")
    public String userLogout(Model model){
        model.addAttribute("token", token);
        return "index";
    }

}
