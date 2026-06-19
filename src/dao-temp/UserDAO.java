package dao;


import database.MysqlConnector;
import java.sql.*;
import javax.swing.JOptionPane;
import model.logindata;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import util.SessionManager;
import view.OwnerBookingsApproval;

public class UserDAO {

    MysqlConnector mysql = new MysqlConnector();

    // REGISTER USER (with BCrypt hashing)
    public boolean createUser(logindata user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(name, username, email, password, role) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            // Use username as name for now since registration form doesn't have separate name field
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getEmail());

            // HASH password before saving
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            pstm.setString(4, hashedPassword);
            pstm.setString(5, "customer"); // Default role

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
  public User checkUser(String usernameOrEmail, String password) {
    Connection conn = mysql.openConnection();
    // Try with new schema first, fallback to old schema
    String sql = "SELECT id, name, username, email, phone, password, role, status FROM users WHERE (username = ? OR email = ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, usernameOrEmail);
        pstmt.setString(2, usernameOrEmail);
        ResultSet result = pstmt.executeQuery();
        if (result.next()) {
            String storedHash = result.getString("password");

            // Guard: if not a valid BCrypt hash, reject login
            if (storedHash == null || !storedHash.startsWith("$2a$") && !storedHash.startsWith("$2b$")) {
                return null;
            }

            if (BCrypt.checkpw(password, storedHash)) {
                User user = new User();
                user.setUserId(result.getInt("id"));
                user.setFullName(result.getString("name"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setRole(result.getString("role"));
                user.setStatus(result.getString("status"));
                return user;
            }
        }
        return null;
    } catch (SQLException ex) {
        // Fallback to old schema if new columns don't exist
        try {
            String oldSql = "SELECT id, username, email, password FROM users WHERE (username = ? OR email = ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(oldSql)) {
                pstmt.setString(1, usernameOrEmail);
                pstmt.setString(2, usernameOrEmail);
                ResultSet result = pstmt.executeQuery();
                if (result.next()) {
                    String storedHash = result.getString("password");

                    // Guard: if not a valid BCrypt hash, reject login
                    if (storedHash == null || !storedHash.startsWith("$2a$") && !storedHash.startsWith("$2b$")) {
                        return null;
                    }

                    if (BCrypt.checkpw(password, storedHash)) {
                        User user = new User();
                        user.setUserId(result.getInt("id"));
                        user.setFullName(result.getString("username")); // Use username as name
                        user.setUsername(result.getString("username"));
                        user.setEmail(result.getString("email"));
                        user.setRole("customer"); // Default role
                        return user;
                    }
                }
                return null;
            }
        } catch (SQLException ex2) {
            System.out.println(ex2);
            return null;
        }
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
public boolean updateUserRole(int userId, String newRole) {
    String sql = "UPDATE users SET role = ? WHERE id = ?";
    try (Connection con = new MysqlConnector().openConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, newRole);
        ps.setInt(2, userId);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
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
    public boolean createOwner(String fullName, String username, String email, String phone) {
    String sql = "INSERT INTO users(name, username, email, phone, password, role) VALUES (?,?,?,?,?,?)";
    try (Connection con = mysql.openConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, fullName);
        ps.setString(2, username);
        ps.setString(3, email);
        ps.setString(4, phone);
        ps.setString(5, BCrypt.hashpw(password, BCrypt.gensalt()));
        ps.setString(6, "owner");
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}