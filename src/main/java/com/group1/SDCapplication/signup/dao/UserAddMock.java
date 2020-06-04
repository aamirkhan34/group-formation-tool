package com.group1.SDCapplication.signup.dao;

import com.group1.SDCapplication.models.User;

public class UserAddMock implements UserAdd{
    @Override
    public String addUserToDB(User user) {
        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        String email = user.getEmail();
        String passWord = user.getPassword();
        if(firstName == "firstName" && lastName == "lastName" && email == "email" && passWord == "passWord" )
        {
            return "success";
        }
        return "Failed";
    }

    @Override
    public boolean userNotExist(String email) {
        if(email == "testemail")
        {
            return true;
        }
        return false;
    }
}
