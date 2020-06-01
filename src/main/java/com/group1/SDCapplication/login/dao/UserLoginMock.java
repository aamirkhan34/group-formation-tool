package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserLoginMock implements UserLogin {
    @Override
    public boolean isUserValid(String username, String password) {
        if(username == "testusername" && password == "testpassword"){
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<String> userRole(String email) {
        List<String> userRole = new ArrayList<>();
        userRole.add("student");
        userRole.add("guest");
        return userRole;
    }

}
