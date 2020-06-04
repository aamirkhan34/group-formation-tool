package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.dao.UserLogin;
import com.group1.SDCapplication.login.dao.UserLoginMock;
import com.group1.SDCapplication.login.dao.UserPasswordReset;
import com.group1.SDCapplication.login.dao.UserPasswordResetMock;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForgotPasswordValidationTest {


    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    UserPasswordReset userPasswordResetDao;

    @BeforeEach
    public void  initialize(){
        userPasswordResetDao = new UserPasswordResetMock();
    }


    @Test
    @Order(1)
    public void getTokenTest(){
        String email = "testemail";
        String token = jwtTokenUtil.generateTokenForgotPassword(email);
        assertTrue(token.equals("") == false);
    }

    @Test
    @Order(2)
    public void getUserId(){
        long uId = userPasswordResetDao.getUserID("testemail");
        assertEquals(uId,1,3);
    }
}
