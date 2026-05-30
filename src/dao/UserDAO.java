package dao;

import Database.MysqlConnector;
import java.sql.*;
import model.logindata;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    MysqlConnector mysql = new MysqlConnector();

    // REGISTER USER (with BCrypt hashing)
    public boolean createUser(logindata user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());

            // HASH password before saving
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            pstm.setString(3, hashedPassword);

            int rows = pstm.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // LOGIN (BCrypt password check)
  public boolean checkUser(String usernameOrEmail, String password) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT password FROM users WHERE (username = ? OR email = ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, usernameOrEmail);
        pstmt.setString(2, usernameOrEmail);
        ResultSet result = pstmt.executeQuery();
        if (result.next()) {
            String storedHash = result.getString("password");

            // Guard: if not a valid BCrypt hash, reject login
            if (storedHash == null || !storedHash.startsWith("$2a$") && !storedHash.startsWith("$2b$")) {
                return false;
            }

            return BCrypt.checkpw(password, storedHash);
        }
        return false;
    } catch (SQLException | IllegalArgumentException ex) {  // <-- catch both!
        System.out.println(ex);
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}
    // CHECK EMAIL EXISTS
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE email=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // SAVE OTP (deletes old OTP first so only 1 OTP per email)
    public boolean saveOTP(String email, String otp) {
        Connection conn = mysql.openConnection();

        // DELETE old OTP first
        String deleteSql = "DELETE FROM password_resets WHERE email=?";
        try (PreparedStatement del = conn.prepareStatement(deleteSql)) {
            del.setString(1, email);
            del.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        // SAVE new OTP with 5 minute expiry
        String sql = "INSERT INTO password_resets(email, otp, expiry_time) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 5 MINUTE))";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, otp);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // VERIFY OTP
    public boolean verifyOTP(String email, String otp) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM password_resets WHERE email=? AND otp=? AND expiry_time > NOW()";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, otp);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // DELETE OTP AFTER USE
    public void deleteOTP(String email) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM password_resets WHERE email=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // RESET PASSWORD (with BCrypt hashing)
    public boolean updatePassword(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE users SET password=? WHERE email=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // HASH new password before saving
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, email);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}