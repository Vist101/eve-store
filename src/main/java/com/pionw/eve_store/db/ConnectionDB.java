package com.pionw.eve_store.db;

import com.pionw.eve_store.http_eve.ReadTextFromURL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionDB {

    public static Connection getConnection() {
        ResourceBundle bundle = ResourceBundle.getBundle("db/connection");
        String url = ReadTextFromURL.readString(bundle.getString("url"));
        String username = ReadTextFromURL.readString(bundle.getString("username"));
        String password = ReadTextFromURL.readString(bundle.getString("password"));
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
