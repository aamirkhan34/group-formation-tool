package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.JwtTokenUtilTest.JwtTokenUtilTest;
import com.group1.SDCapplication.login.dao.UserLoginDao;
import com.group1.SDCapplication.login.dao.UserLoginDaoTest;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.models.UserCredentialsTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserValidationTest {

    UserLoginDao userLoginDao = new UserLoginDao();
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    @Test
    @Order(1)
    public void userValidationTest(){
        String email = "testemail";
        String password = "testpassword";
        boolean result = userLoginDao.isUserValid(email,password);
        assertTrue(result == false);
    }

    @Test
    @Order(2)
    public void generateTokenTest(){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("testemail");
     assertNotNull(jwtTokenUtil.generateToken(userCredentials));
    }

}
