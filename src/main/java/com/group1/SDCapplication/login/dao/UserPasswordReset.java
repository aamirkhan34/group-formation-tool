package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPasswordReset {

    DevDatabase dev = new DevDatabase();

    public boolean passwordReset(String userEmail, String userPassword){
        PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();
        String encryptedPassword = passwordEncryptDecrypt.passwordEncrypt(userPassword);
        if(updatePassword(userEmail, encryptedPassword)){
            System.out.println("password reset success");
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updatePassword(String email, String encryptedPassword){
        long userId = getUserID(email);
        String PASSWORD_UPDATE_QUERY = "UPDATE user set password= '" + encryptedPassword + "' where UID =" + userId;
        System.out.println(PASSWORD_UPDATE_QUERY);
        Statement stmt = null;
        try {
            Connection devConnection = dev.getConnection();
            stmt = devConnection.createStatement();
            int rs = stmt.executeUpdate(PASSWORD_UPDATE_QUERY);
            if(rs == 1)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getUserID(String email){
        long userId = 0;
        String SELECT_QUERY = "SELECT UID FROM user where email=" + "'" + email +"'";
        try {
            Connection devConnection = dev.getConnection();
            Statement stmt = devConnection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_QUERY);
            while (rs.next()){
                userId = rs.getLong("UID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return userId;
    }
}
