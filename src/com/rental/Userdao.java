package com.rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String INSERT =
        "INSERT INTO users (first_name, last_name, email, phone, password, role) VALUES (?,?,?,?,?,?)";
    private static final String CHECK_EMAIL =
        "SELECT id FROM users WHERE email = ?";

    public boolean registerUser(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            // check duplicate email
            try (PreparedStatement ps = conn.prepareStatement(CHECK_EMAIL)) {
                ps.setString(1, user.getEmail());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return false;
                }
            }
            // insert
            try (PreparedStatement ps = conn.prepareStatement(INSERT)) {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getRole());
                ps.executeUpdate();
            }
            return true;
        }
    }
}
