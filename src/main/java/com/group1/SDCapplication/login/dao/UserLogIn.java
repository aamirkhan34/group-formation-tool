package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.models.User;

public interface UserLogIn {
    public boolean isUserValid(String username, String password);
    public User getUser(String  email);
}
