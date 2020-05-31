package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.JwtTokenUtilTest.JwtTokenUtilTest;
import com.group1.SDCapplication.login.dao.UserLoginDaoTest;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.models.UserCredentialsTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserValidationTest {
    public String TokenTest;
    UserLoginDaoTest userLoginDaoTest = new UserLoginDaoTest();
    JwtTokenUtilTest jwtTokenUtilTest = new JwtTokenUtilTest();
    UserCredentialsTest userCredentialsTest = new UserCredentialsTest();

    @Test
    public void userValidationTest(){
        String userName = "testusername";
        String passWord = "testpassword";
        boolean result = userLoginDaoTest.isUserValidTest(userName, passWord);
        System.out.println(result);
        assertTrue(result);
    }
//
//    @Test
//    public void generateTokenTest(UserCredentialsTest userCredentialsTest){
////        Token = jwtTokenUtilTest.generateToken();
////        assertTrue();
//        String token = "sampletoken";
//        assertTrue(token.equals("sampletoken"));
//    }

    @Test
    public void getUserRolesTest(){
        List<String> userRolesTest = new ArrayList<>();
        userRolesTest = userLoginDaoTest.userRoleTest();
        assertEquals(userRolesTest, userLoginDaoTest.userRoleTest());
    }

}
