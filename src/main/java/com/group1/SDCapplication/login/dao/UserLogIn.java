package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.login.models.UserCredentials;

public interface UserLogIn {
    public boolean isUserValid(String username, String password);
}
