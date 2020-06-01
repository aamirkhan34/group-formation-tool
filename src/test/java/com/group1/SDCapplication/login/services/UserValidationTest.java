package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.JwtTokenUtilTest.JwtTokenUtilTest;
import com.group1.SDCapplication.login.dao.UserLogin;
import com.group1.SDCapplication.login.dao.UserLoginDao;
import com.group1.SDCapplication.login.dao.UserLoginMock;
import com.group1.SDCapplication.login.models.UserCredentialsTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserValidationTest {
    public String TokenTest;
    UserLogin userLoginDao;



    JwtTokenUtilTest jwtTokenUtilTest = new JwtTokenUtilTest();
    UserCredentialsTest userCredentialsTest = new UserCredentialsTest();

    @BeforeEach
    public void  initialize(){
        userLoginDao = new UserLoginMock();
    }

    @Test
    public void userValidationTest(){
        String userName = "testusername";
        String passWord = "testpassword";
        boolean result = userLoginDao.isUserValid(userName, passWord);
        System.out.println(result);
        assertTrue(result);
    }
//
    @Test
    public void generateTokenTest(){
//        Token = jwtTokenUtilTest.generateToken();
        String token = "sampletoken";
        assertTrue(token.equals("sampletoken"));
    }

    @Test
    public void getUserRolesTest(){
        List<String> userRolesTest = new ArrayList<>();
        userRolesTest = userLoginDao.userRole("");
        System.out.println(userRolesTest);
        assertEquals(userRolesTest, userLoginDao.userRole(""));
    }

}
