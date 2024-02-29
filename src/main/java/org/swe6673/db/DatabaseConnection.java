package org.swe6673.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:8889/task_manager";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
        return connection;
    }



    public void fetchData() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Successfully connected to the database.");
                // Execute SQL queries using conn
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
