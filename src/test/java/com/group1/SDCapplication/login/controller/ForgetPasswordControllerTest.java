package com.group1.SDCapplication.login.controller;

import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgetPasswordControllerTest {
    ForgetPasswordController forgetPasswordController = null;
    @BeforeEach
    public void initialize(){
        forgetPasswordController = new ForgetPasswordController();
    }
    @Test
    public void testForgetPassword(){
        assertTrue(forgetPasswordController.forgetPassword(new ConcurrentModel()).equals("forgetPassword"), ()->"page transfer error");
    }
    @Test
    public void testSendEmailWithPassword(){
        User user = new User();
        user.setId(1);
        user.setFirstname("test");
        user.setLastname("family");
        user.setEmail("testEmail");
        user.setPassword("testPassword");

        assertTrue(forgetPasswordController.sendEmailWithPassword(user, new ConcurrentModel()).equals("forgetPassword"), ()->"page transfer error");

    }

}
