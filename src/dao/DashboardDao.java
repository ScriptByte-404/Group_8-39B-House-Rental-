/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MysqlConnector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import model.DashboardData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DashboardDao {

    public ArrayList<DashboardData> getUsers() {

        ArrayList<DashboardData> list = new ArrayList<>();

        String sql = "SELECT username, email, role, status FROM users";

        try (Connection conn = (Connection) MysqlConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                DashboardData data = new DashboardData(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("status")
                );

                list.add(data);
            }

        } catch (SQLException e) {
            // important for debugging
            
        }

        return list;
    }
}

