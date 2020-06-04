package com.group1.SDCapplication.signup.dao;
import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.datasource.ProductionDatabase;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserAddDao  implements UserAdd{
    ProductionDatabase dev = new ProductionDatabase();
    @Override
    public String addUserToDB(User user) {
        PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();
        String result = "";
        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        String email = user.getEmail();
        String password = user.getPassword();
        String encryptedPassword = passwordEncryptDecrypt.passwordEncrypt(password);
        if(userNotExist(email)) {
            String USER_INSERT_QUERY = "INSERT INTO user (first_name, last_name, email, password) VALUES (" + "'" + firstName + "'" + "," + "'" + lastName + "'" + "," + "'" + email + "'" + "," + "'" + encryptedPassword + "'" + ")";
            try {
                int res;
                Connection devConnection = dev.getConnection();
                Statement stmt = devConnection.createStatement();
                res = stmt.executeUpdate(USER_INSERT_QUERY);
                String ID_ROLE_QUERY = "Select UID from user where email = "+ "'" + email + "'";
                ResultSet rs = stmt.executeQuery(ID_ROLE_QUERY);
                int userID = 0;
                while (rs.next())
                {
                    userID = rs.getInt("UID");
                }
                String ROLE_INSERT_QUERY = "INSERT INTO user_role (UID, role_id) VALUES (" + "'" + userID + "'" + "," + "5)";
                stmt.executeUpdate(ROLE_INSERT_QUERY);
                if (res == 1) {
                    result = "Signup Successful";
                    devConnection.close();
                } else {
                    result = "Signup is not Successful";
                    devConnection.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return result;
        }
        else {
            result = "Mail id already exists";
            return result;
        }
    }

    @Override
    public boolean userNotExist(String email){
        String USER_EXIST = "SELECT email FROM user where email =" + "'"+ email +"'";
        boolean result = false;
        ResultSet rs = null;
        try {
            Connection devConnection = dev.getConnection();
            Statement stmt = devConnection.createStatement();
            rs = stmt.executeQuery(USER_EXIST);
            if(rs.next()){
                result = false;
                devConnection.close();
            }
            else {
                result = true;
                devConnection.close();
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
