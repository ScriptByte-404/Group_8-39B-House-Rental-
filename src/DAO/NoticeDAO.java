package dao;

import database.MysqlConnector;
import model.Notice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {

    private MysqlConnector connector = new MysqlConnector();

    /**
     * Inserts a new notice with the given title and message.
     * Returns true if the insert succeeded.
     */
    public boolean insertNotice(String title, String message) {
        String sql = "INSERT INTO notices (title, message) VALUES (?, ?)";

        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, message);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns all notices, most recent first.
     */
    public List<Notice> getAllNotices() {
        List<Notice> list = new ArrayList<>();
        String sql = "SELECT id, title, message, created_at FROM notices ORDER BY created_at DESC";

        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Notice n = new Notice(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at")
                );
                list.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}