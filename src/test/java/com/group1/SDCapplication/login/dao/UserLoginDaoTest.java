package com.group1.SDCapplication.login.dao;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserLoginDaoTest  implements UserLogInTest{

    @Override
    public boolean isUserValidTest(String username, String password) {
        if(username == "testusername" && password == "testpassword"){
            return true;
        }
        return false;
    }

    public List<String> userRoleTest(){
        List<String> userRole = new ArrayList<>();
        userRole.add("student");
        userRole.add("guest");
        return userRole;
    }


}
