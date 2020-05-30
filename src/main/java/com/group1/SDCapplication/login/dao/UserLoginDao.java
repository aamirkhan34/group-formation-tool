package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDao implements UserLogIn{

    DevDatabase dev = new DevDatabase();
    PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();

    public boolean isUserValid(String userName, String password){
        String USER_SELECT_QUERY = "SELECT email, password from user where email = "+ "'" + userName + "'";
        try {
            String userPassword = null;
            Connection devConnection = dev.getConnection();
            Statement stmt = devConnection.createStatement();
            ResultSet rs = stmt.executeQuery(USER_SELECT_QUERY);
            while (rs.next()){
                userPassword = passwordEncryptDecrypt.passwordDecrypt(rs.getString("password"));
            }
            if(password.equals(userPassword)){
                return true;
            }
            else {
                return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public List<String> userRole(String userName){

        String USER_ROLE_QUERY = "select rl.role_name as role_name from user as us inner join role as rl inner join user_role as ur \n" +
                "on us.UID = ur.UID and ur.role_id = rl.role_id where us.email = "+"'"+ userName + "'";
        Connection devConnection = null;
        List<String> userRole = new ArrayList<>();
        try {
            devConnection = dev.getConnection();
            Statement stmt = devConnection.createStatement();
            ResultSet rs = stmt.executeQuery(USER_ROLE_QUERY);
            while (rs.next()){
                    String role = rs.getString("role_name");
                    userRole.add(role);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userRole;
    }
}
