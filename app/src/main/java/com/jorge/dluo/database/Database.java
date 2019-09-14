package com.jorge.dluo.database;

import java.sql.*;

public class Database {

    Connection connection;

    public static void connect() {
        try
        {
            Class.forName("com.imaginary.sql.msql.MsqlDriver");

            String url = "jdbc:msql://dbserver.com:1114/contact_mgr";
            Connection conn = DriverManager.getConnection(url,"user1","password");
        }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void sendRequest(String sqlRequest) {




    }

}