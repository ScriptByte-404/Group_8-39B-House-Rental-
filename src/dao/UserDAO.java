package dao;

import Database.MysqlConnector;
import java.sql.*;
import model.logindata;

public class UserDAO {

    MysqlConnector mysql = new MysqlConnector();

    // ✅ REGISTER USER
    public boolean createUser(logindata user) {

        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(username, email, password) VALUES (?,?,?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());

            int rows = pstm.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {
            mysql.closeConnection(conn);
        }
    }

    // 🔥 FIXED LOGIN (NOW CHECKS PASSWORD TOO)
    public boolean checkUser(String usernameOrEmail, String password) {

        Connection conn = mysql.openConnection();

        String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usernameOrEmail);
            pstmt.setString(2, usernameOrEmail);
            pstmt.setString(3, password);

            ResultSet result = pstmt.executeQuery();

            return result.next(); // true = login success

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;

        } finally {
            mysql.closeConnection(conn);
        }
    }

}