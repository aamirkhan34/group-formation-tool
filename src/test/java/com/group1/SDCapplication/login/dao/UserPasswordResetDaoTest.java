package com.group1.SDCapplication.login.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserPasswordResetDaoTest {


    @Test
    public void passwordReset(){
        UserPasswordResetDao userPasswordResetDao = new UserPasswordResetDao();
        boolean result = userPasswordResetDao.passwordReset("testemail", "testpassword");
        assertFalse(result, ()-> "error while changing password");
    }


}
