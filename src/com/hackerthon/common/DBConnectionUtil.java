package com.hackerthon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import com.hackerthon.common.ConfigureLoader;

public class DBConnectionUtil extends ConfigureLoader {

    private static DBConnectionUtil instance;
    private Connection connection;

    private DBConnectionUtil() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionUtil getInstance() {
        if (instance == null) {
            synchronized (DBConnectionUtil.class) {
                if (instance == null) {
                    instance = new DBConnectionUtil();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}