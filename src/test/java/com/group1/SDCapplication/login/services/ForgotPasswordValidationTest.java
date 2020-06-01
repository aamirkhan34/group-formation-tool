package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForgotPasswordValidationTest {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Test
    public void getTokenTest(){
        String email = "testemail";
        String token = jwtTokenUtil.generateTokenForgotPassword(email);
        assertTrue(token.equals("") == false);
    }
}
