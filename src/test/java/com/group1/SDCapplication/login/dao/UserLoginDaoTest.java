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




//    @Override
//    public boolean isUserValidTest(String username, String password) {
//        if(username == "testusername" && password == "testpassword"){
//            return true;
//        }
//        return false;
//    }
//
//    public List<String> userRoleTest(){
//        List<String> userRole = new ArrayList<>();
//        userRole.add("student");
//        userRole.add("guest");
//        return userRole;
//    }
    @Test
    public void testGetUser(){
        User user = userLoginDao.getUser("premkumar.menni@dal.ca");
        assertFalse(null == user, () -> "get User correctly");
    }


}
