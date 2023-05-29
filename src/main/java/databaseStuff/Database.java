package databaseStuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "student";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }

    public static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }

    public static void rollback() {
    }
}
