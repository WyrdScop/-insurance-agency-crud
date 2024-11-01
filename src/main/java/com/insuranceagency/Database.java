package main.java.com.insuranceagency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Database {

    // Method to get a new connection every time it's called
    public static Connection getConnection() {
        Connection connection = null;  // Local variable to ensure new instance each time
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection; // Return a new connection instance each time
    }
}