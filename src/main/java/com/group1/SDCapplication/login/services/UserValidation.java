package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.dao.UserLoginDao;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {
    private String token;
    UserLoginDao userLoginDao = new UserLoginDao();
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    public boolean userValidation(UserCredentials userCredentials){
        String userName = userCredentials.getEmail();
        String passWord = userCredentials.getPassword();
        boolean result = userLoginDao.isUserValid(userName, passWord);
        return result;
    }
    public String generateToken(User userCredentials){
        token = jwtTokenUtil.generateToken(userCredentials);
        return token;
    }
    public List<String> getUserRoles(UserCredentials userCredentials){
        List<String> userRoles = new ArrayList<>();
        String userName = userCredentials.getEmail();
        userRoles = userLoginDao.userRole(userName);
        return userRoles;
    }
}
