package com.group1.SDCapplication.login.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserCredentialsTest {

    public String testEmail = "testusername";

    public String testPassword = "testpassword";

    public String getTestEmail() {
        return testEmail;
    }

    public void setTestEmail(String testEmail) {
        this.testEmail = testEmail;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }



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
