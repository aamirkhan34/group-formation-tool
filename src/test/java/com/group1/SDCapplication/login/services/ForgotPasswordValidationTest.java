package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForgotPasswordValidationTest {


    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();


    @Test
    @Order(1)
    public void getTokenTest(){
        String email = "testemail";
        String token = jwtTokenUtil.generateTokenForgotPassword(email);
        assertTrue(token.equals("") == false);
    }
}
