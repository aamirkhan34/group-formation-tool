package com.group1.SDCapplication.Datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseInterface {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
