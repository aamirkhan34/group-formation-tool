package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDao implements UserLogin {
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
                devConnection.close();
                return true;
            }
            else {
                devConnection.close();
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

    @Override
    public User getUser(String  email) {
        String USER_SELECT_QUERY = "SELECT UID, first_name,last_name, email from user where email = ?";
        User user = null;
        try{
            PreparedStatement statement = dev.getConnection().prepareStatement(USER_SELECT_QUERY);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getLong("UID"));
            user.setFirstname(rs.getString("first_name"));
            user.setLastname(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
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
            devConnection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userRole;
    }

    public boolean isUserValid(String email){
        String USER_SELECT_QUERY = "SELECT email from user where email = "+ "'" + email + "'";
        try {
            String userEmail = null;
            Connection devConnection = dev.getConnection();
            Statement stmt = devConnection.createStatement();
            ResultSet rs = stmt.executeQuery(USER_SELECT_QUERY);
            while (rs.next()){
                userEmail = rs.getString("email");
            }
            if(email.equals(userEmail)){
                devConnection.close();
                return true;
            }
            else {
                devConnection.close();
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
}
