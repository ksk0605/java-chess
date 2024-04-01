package dao;

import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionGenerator {
    public static Connection getConnection() {
        try {
            final DatabaseConfig databaseConfig = new DatabaseConfig();
            return DriverManager.getConnection(
                    "jdbc:mysql://" + databaseConfig.getServer() + "/" + databaseConfig.getDatabase() + databaseConfig.getOption(),
                    databaseConfig.getUsername(),
                    databaseConfig.getPassword());
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
