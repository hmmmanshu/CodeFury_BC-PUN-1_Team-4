package com.codefury.bugtracking.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class to create a database connection and return it to the caller.
 */
public class DatabaseConnection {

    private DatabaseConnection() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Retrieves a database connection using the configuration properties.
     *
     * @return A database connection.
     */
    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();

        // Load database configuration properties from the "Config.properties" file.
        try (FileReader fileReader = new FileReader("Config.properties");) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Load the MySQL JDBC driver class.
            Class.forName(properties.getProperty("mysqlJDBCDriver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            // Establish a connection to the database using the provided URL, username, and password.
            connection = DriverManager.getConnection(properties.getProperty("jdbcUrl"),
                    properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return connection;
    }
}
