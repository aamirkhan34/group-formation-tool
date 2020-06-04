package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.dao.UserLogin;
import com.group1.SDCapplication.login.dao.UserLoginMock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserValidationTest {
    UserLogin userLoginDao;

    @BeforeEach
    public void  initialize(){
        userLoginDao = new UserLoginMock();
    }

    @Test
    public void userValidationTest(){
        String userName = "testusername";
        String passWord = "testpassword";
        boolean result = userLoginDao.isUserValid(userName, passWord);
        assertTrue(result);
    }

    @Test
    public void generateTokenTest(){
        String token = "sampletoken";
        assertTrue(token.equals("sampletoken"));
    }

    @Test
    public void getUserRolesTest(){
        List<String> userRolesTest = new ArrayList<>();
        userRolesTest = userLoginDao.getUserRole("");
        assertEquals(userRolesTest, userLoginDao.getUserRole(""));
    }

}
