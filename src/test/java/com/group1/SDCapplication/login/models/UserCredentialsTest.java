package com.group1.SDCapplication.login.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserCredentialsTest {

    @Test
    public void setEmailtest(){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("testusername");
        assertTrue(userCredentials.getEmail().equals("testusername"));
    }

    @Test
    public void setPasswordTest(){
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setPassword("testpassword");
        assertTrue(userCredentials.getPassword().equals("testpassword"));
    }
}
