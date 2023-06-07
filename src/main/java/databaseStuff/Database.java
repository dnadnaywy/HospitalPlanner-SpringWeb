package databaseStuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TODO: jpa entity + connections with tables
//TODO: hikari connection pool
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
        System.out.println("nuup");
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public static void rollback() {
    }
}
