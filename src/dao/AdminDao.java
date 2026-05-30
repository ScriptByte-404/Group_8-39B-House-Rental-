/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MysqlConnector;
import java.sql.*;
import model.Admindata;
import model.logindata;

/**
 *
 * @author rojan
 */
public class AdminDao {
    MysqlConnector mysql = new MysqlConnector();

    // ✅ REGISTER USER
    public boolean createUser(logindata user) {

        Connection conn = mysql.openConnection();
        String sql;
        sql = "INSERT INTO users(username, password) VALUES (?,?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getUsername());
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

    /**
     *
     * @param usernameOrEmail
     * @param password
     * @return
     */
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
