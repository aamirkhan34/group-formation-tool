package core.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductionDatabase implements DatabaseInterface{
    final String HOST = "jdbc:mysql://db-5308.cs.dal.ca:3306/";
    final String  DB = "CSCI5308_1_PRODUCTION";
    final String  USER = "CSCI5308_1_PRODUCTION_USER";
    final String  PASS = "CSCI5308_1_PRODUCTION_1841";
    final String  ARGs = "?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";

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
        Connection conn = DriverManager.getConnection(HOST+DB+ARGs,USER, PASS);
        return conn;
    }
}
