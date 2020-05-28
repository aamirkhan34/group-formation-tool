package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.models.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CURD_Operation {


    public User DEv_Connection()  {
//        String CREATE_TABLE_SQL = "create table users" +
//                "(UID int NOT NULL, " + "FIRSTNAME varchar(45) NOT NULL," + "LASTNAME varchar(45) NOT NULL, " +
//                "EMAIL varchar(45) NOT NULL, " + "PASSWORD varchar(100) NOT NULL," + "PRIMARY KEY (UID))";
//        String INSERT_QUERY = "INSERT INTO users VALUES (5,'prem', 'menni','ABCD@Gmail.com','abcd')";
        String select_query = "SELECT UID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD FROM users";
        ResultSet rs;

        User u = new User();
        List<User> user_records;
        try {
            DevDatabase dev = new DevDatabase();
            Connection dev_conn = dev.getConnection();
            Statement stmt = dev_conn.createStatement();
//            stmt.executeUpdate(CREATE_TABLE_SQL);
//            stmt.executeUpdate(INSERT_QUERY);
            System.out.println("table created");
            rs = stmt.executeQuery(select_query);
            while (rs.next()){
                int id = rs.getInt("UID");
                u.setId(id);
                String first_name = rs.getString("FIRSTNAME");
                u.setFirstname(first_name);
                String last_name = rs.getString("LASTNAME");
                u.setLastname(last_name);
                String email = rs.getString("EMAIL");
                u.setEmail(email);
                String password = rs.getString("PASSWORD");
                u.setPassword(password);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        user_records.add(u);
        System.out.println(u.getFirstname());
        return u;
    }

}
