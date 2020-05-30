package com.group1.SDCapplication.signup.services;

import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.dao.UserAddDao;

public class UserSignup {
    UserAddDao userAddDao =  new UserAddDao();
    public String addNewUser(User user)
    {
        String result = userAddDao.addUserToDB(user);
        return result;
    }
}
