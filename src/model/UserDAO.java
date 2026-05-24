package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addUser(logindata user) throws SQLException {
        String sql = "INSERT INTO users (username, email, phone, password, role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPhone());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getRole());
        return ps.executeUpdate() > 0;
    }

    public logindata getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            logindata user = new logindata(
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getString("role")
            );
            user.setUserId(rs.getInt("user_id"));
            return user;
        }
        return null;
    }
}
git add src/model/UserDAO.java
git commit -m "Add UserDAO class with addUser and getUserByUsername methods"





git status

