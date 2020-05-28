package com.group1.SDCapplication.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabase implements DatabaseInterface {
    final String HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/";
    final String  TEST_DB = "CSCI5308_1_DEVINT";
    final String  ARGs = "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    final String  TEST_USER = "CSCI5308_1_TEST_USER";
    final String  TEST_PASS = "CSCI5308_1_TEST_1441";

    private static TestDatabase database = null;
    public static TestDatabase getInstance(){
        if (database == null){
            database = new TestDatabase();
        }
        return database;
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(HOST+TEST_DB+ARGs,TEST_USER, TEST_PASS);
        return conn;
    }
}
