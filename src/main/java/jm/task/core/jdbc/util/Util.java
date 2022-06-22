package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/mydb", "rusy", "Some123Password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
