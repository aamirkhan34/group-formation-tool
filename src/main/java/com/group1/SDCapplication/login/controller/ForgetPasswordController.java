package com.group1.SDCapplication.login.controller;


import com.group1.SDCapplication.login.dao.UserLoginDao;
import com.group1.SDCapplication.login.services.ForgotPasswordValidation;
import com.group1.SDCapplication.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
        model.addAttribute("emailError", false);
        return "/Login/ForgetPassword";
    }

    @PostMapping("/forgetpassword")
    public String Send_Email_with_password(@ModelAttribute User user, Model model)
    {
        ForgotPasswordValidation forgotPasswordValidation = new ForgotPasswordValidation();
        UserLoginDao userLoginDao = new UserLoginDao();
        model.addAttribute("user", user);
        if(userLoginDao.isUserValid(user.getEmail()))
        {
            String token = forgotPasswordValidation.getToken(user.getEmail());
            if(sendEmail(user.getEmail(), token))
            {
                return "/index";
            }
            else {
                return "/Login/ForgetPassword";
            }
        }
        else {
            String emailError = "Email does not exist in our system";
            model.addAttribute("emailError", emailError);
            return "/Login/ForgetPassword";
        }
    }
    public boolean sendEmail(String userEmail, String token){
        try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setTo(userEmail);
            simpleMessage.setSubject("Password Reset email for SDC application");
            String url = "Please find the link provided to reset your password. Linke will be " +
                    "active for 10minutes" +
                    "      " + "http://localhost:8080/user/passwordreset?token=" + token;
            simpleMessage.setText(url);
            javaMailSender.send(simpleMessage);
            return true;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
