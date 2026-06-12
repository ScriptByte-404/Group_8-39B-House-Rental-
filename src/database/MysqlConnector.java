package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector implements db {

    private static final String username = "root";
    private static final String password = "cscorner";
    private static final String database = "house_rental_system";

    private static Connection connection;

    // =========================
    // 1. OPEN CONNECTION
    // =========================
    @Override
    public Connection openConnection() {
        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + database +
                        "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        username,
                        password
                );

                System.out.println("Database connection success");
            }

            return connection;

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
    }

    // =========================
    // 2. CLOSE CONNECTION
    // =========================
    @Override
    public void closeConnection(Connection conn) {

        try {

            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }

        } catch (SQLException e) {
            System.out.println("Close error: " + e.getMessage());
        }
    }

    // =========================
    // 3. RUN SELECT QUERY
    // =========================
    @Override
    public ResultSet runQuery(Connection conn, String query) {

        try {

            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Query error: " + e.getMessage());
            return null;
        }
    }

    // =========================
    // 4. RUN INSERT/UPDATE/DELETE
    // =========================
    @Override
    public int excecuteUpdate(Connection conn, String query) {

        try {

            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return -1;
        }
    }
}