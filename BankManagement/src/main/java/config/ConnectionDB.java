package config;

import util.application.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private static Connection connection;

    static {
        try {
            connection = (Connection) DriverManager.getConnection(ApplicationProperties.URL_DB, ApplicationProperties.UserName, ApplicationProperties.password);

        } catch (Exception e) {
            System.out.println("Error in DataBase Connection!");
        }
    }

    public static Connection getConnection() {
        try {
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
