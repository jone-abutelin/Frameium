package com.frameium.utilities.DatabaseUtils;

import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabaseUtil {
    private static Connection connection;
    private WebDriver driver;
    private static String relativePath = "./configFiles/config.properties"; // Adjust based on your project structure


    public MySQLDatabaseUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Method to load database configurations dynamically
    private static Properties loadDBProperties() {
        Properties properties = new Properties();
        try (InputStream input = MySQLDatabaseUtil.class.getClassLoader().getResourceAsStream("configFiles/config.properties")) {
            if (input == null) {
                System.err.println("config.properties file not found in classpath under configFiles");
                return properties;
            }
            properties.load(input);
            System.out.println("Database configuration loaded successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load database configuration from classpath under configFiles");
            e.printStackTrace();
        }
        return properties;
    }

    // Method to get connection to database
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties properties = loadDBProperties();
            String dbUrl = properties.getProperty("db.url");
            System.out.println("DB URL: " + properties.getProperty("db.url"));
            String dbUser = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            // Check if db.url is null or empty
            if (dbUrl == null || dbUrl.isEmpty()) {
                throw new SQLException("Database URL is null or empty");
            }
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        }
        return connection;
    }

    // Method to get connection to MySQL without database
    public static Connection getConnectionWithoutDB() throws SQLException {
        Properties properties = loadDBProperties();
        String dbUrl = properties.getProperty("db.url").split("/")[0];  // Remove the database part
        String dbUser = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");

        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);  // Connect without database
    }

    // Method to get connection to a specific database dynamically
    public static Connection getConnectionForDB(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        // Validate inputs
        if (dbUrl == null || dbUrl.isEmpty()) {
            throw new SQLException("Database URL is null or empty. Provide a valid URL.");
        }
        if (dbUser == null || dbUser.isEmpty()) {
            throw new SQLException("Database username is null or empty. Provide a valid username.");
        }
        if (dbPassword == null) {
            dbPassword = "";
        }
        // Establish and return the connection
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }


    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection");
            e.printStackTrace();
        }
    }

    // Method to check database is available or not
    public static boolean isDatabaseAvailable(String dbName) {
        try (Connection connection = MySQLDatabaseUtil.getConnection()) {
            return connection.getCatalog().equals(dbName);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
