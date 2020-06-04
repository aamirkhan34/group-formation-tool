package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLoginDaoTest  {

    private UserLoginDao userLoginDao;

    @BeforeEach
    public void initialize() {
        userLoginDao = new UserLoginDao();
    }


    @Test
    public void isUserValidTest() {
        UserLoginDao userLoginDao = new UserLoginDao();
        User user = new User();
        user.setEmail("testemail");
        user.setPassword("testpassword");
        assertTrue((user.getEmail() == "testemail" && user.getPassword() == "testpassword") == true);
        assertTrue(userLoginDao.isUserValid(user.getEmail(), user.getPassword()) == false);
    }

    @Test
    public void isUserValid(){
        UserLoginDao userLoginDao = new UserLoginDao();
        assertTrue(userLoginDao.isUserValid("testemail") == false);
    }

}
