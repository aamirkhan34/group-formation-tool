package com.group1.SDCapplication.login.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginControllerTest {
    private MockMvc mockMvc;
    @Test
    public void testLogin(){
        LoginController loginController = new LoginController();
        assertTrue(loginController.login(new ConcurrentModel()).equals("login"),()->"page transfer error");
    }
}
