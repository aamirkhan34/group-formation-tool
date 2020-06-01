package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserLoginDaoTest  {

    private UserLoginDao userLoginDao;
    @BeforeEach
    public void initialize() {
        userLoginDao = new UserLoginDao();
    }


    @Test
    public void testGetUser(){
        User user = userLoginDao.getUser("premkumar.menni@dal.ca");
        assertFalse(null == user, () -> "get User correctly");
    }


}
