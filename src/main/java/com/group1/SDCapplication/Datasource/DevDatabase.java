package com.group1.SDCapplication.Datasource;

// import org.springframework.context.annotation.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DevDatabase implements DatabaseInterface{
    final String HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/";
    final String  DB = "CSCI5308_1_DEVINT";
    final String  USER = "CSCI5308_1_DEVINT_USER";
    final String  PASS = "CSCI5308_1_DEVINT_1161";
    final String  ARGs = "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";

    private static DevDatabase database = null;
    public static DevDatabase getInstance(){
        if (database == null){
            database = new DevDatabase();
        }
        return database;
    }

    @Override
    public  Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(HOST+DB+ARGs,USER, PASS);
        return conn;
    }
}
