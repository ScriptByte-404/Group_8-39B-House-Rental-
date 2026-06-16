package dao;

import database.MysqlConnector;
import model.House;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseDAO {

    private MysqlConnector connector = new MysqlConnector();

    public void insertHouse(House h) {
        // Try with new schema first
        String sql = "INSERT INTO houses (owner_id, name, location, price, type, bedrooms, bathrooms, kitchens, description, amenities, images, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, h.getOwnerId());
            ps.setString(2, h.getName());
            ps.setString(3, h.getLocation());
            ps.setString(4, h.getPrice());
            ps.setString(5, h.getType());
            ps.setString(6, h.getBedrooms());
            ps.setString(7, h.getBathrooms());
            ps.setString(8, h.getKitchens());
            ps.setString(9, h.getDescription());
            ps.setString(10, String.join(",", h.getAmenities()));
            ps.setString(11, String.join(",", h.getImages()));
            ps.setString(12, h.getStatus() != null ? h.getStatus() : "pending");

            ps.executeUpdate();

        } catch (SQLException e) {
            // Fallback to old schema if new columns don't exist
            try {
                String oldSql = "INSERT INTO houses (name, location, price, type, bedrooms, bathrooms, kitchens, description, amenities, images) "
                              + "VALUES (?,?,?,?,?,?,?,?,?,?)";
                try (Connection con = connector.openConnection();
                     PreparedStatement ps = con.prepareStatement(oldSql)) {

                    ps.setString(1, h.getName());
                    ps.setString(2, h.getLocation());
                    ps.setString(3, h.getPrice());
                    ps.setString(4, h.getType());
                    ps.setString(5, h.getBedrooms());
                    ps.setString(6, h.getBathrooms());
                    ps.setString(7, h.getKitchens());
                    ps.setString(8, h.getDescription());
                    ps.setString(9, String.join(",", h.getAmenities()));
                    ps.setString(10, String.join(",", h.getImages()));

                    ps.executeUpdate();
                }
            } catch (SQLException e2) {
                // Fallback to even older schema without images column
                try {
                    String olderSql = "INSERT INTO houses (name, location, price, type, bedrooms, bathrooms, kitchens, description, amenities) "
                                  + "VALUES (?,?,?,?,?,?,?,?,?)";
                    try (Connection con = connector.openConnection();
                         PreparedStatement ps = con.prepareStatement(olderSql)) {

                        ps.setString(1, h.getName());
                        ps.setString(2, h.getLocation());
                        ps.setString(3, h.getPrice());
                        ps.setString(4, h.getType());
                        ps.setString(5, h.getBedrooms());
                        ps.setString(6, h.getBathrooms());
                        ps.setString(7, h.getKitchens());
                        ps.setString(8, h.getDescription());
                        ps.setString(9, String.join(",", h.getAmenities()));

                        ps.executeUpdate();
                    }
                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public List<House> getAllHouses() {
        try {
            return queryHouses("SELECT * FROM houses", null);
        } catch (Exception e) {
            // Fallback to old schema if new columns don't exist
            return queryHousesOld("SELECT * FROM houses", null);
        }
    }

    public List<House> getApprovedHouses() {
        try {
            return queryHouses("SELECT * FROM houses WHERE status = 'approved'", null);
        } catch (Exception e) {
            // Fallback to old schema - return all houses since status column doesn't exist
            return queryHousesOld("SELECT * FROM houses", null);
        }
    }

    public List<House> getApprovedHouses(int limit) {
        try {
            return queryHouses("SELECT * FROM houses WHERE status = 'approved' LIMIT ?", null, limit);
        } catch (Exception e) {
            // Fallback to old schema
            return queryHousesOld("SELECT * FROM houses LIMIT ?", null, limit);
        }
    }

    public List<House> getPendingHouses() {
        try {
            return queryHouses("SELECT * FROM houses WHERE status = 'pending'", null);
        } catch (Exception e) {
            // Fallback to old schema - return empty list since status column doesn't exist
            return new ArrayList<>();
        }
    }

   public List<Integer> getHouseIdsByOwner(int ownerId) {
    List<Integer> ids = new ArrayList<>();

    String sql = "SELECT id FROM houses WHERE owner_id = ?";

    try(Connection con = connector.openConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, ownerId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            ids.add(rs.getInt("id"));
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return ids;
}

    public List<House> searchHouses(String keyword) {
        try {
            String sql = "SELECT * FROM houses WHERE (name LIKE ? OR location LIKE ?) AND status = 'approved'";
            return queryHouses(sql, keyword);
        } catch (Exception e) {
            // Fallback to old schema
            String sql = "SELECT * FROM houses WHERE (name LIKE ? OR location LIKE ?)";
            return queryHousesOld(sql, keyword);
        }
    }

    public boolean updateStatus(int houseId, String status) {
        String sql = "UPDATE houses SET status = ? WHERE id = ?";
        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, houseId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<House> queryHouses(String sql, String keyword) {
        return queryHouses(sql, keyword, -1);
    }

    private List<House> queryHouses(String sql, String keyword, int limitParam) {
        List<House> list = new ArrayList<>();

        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int paramIndex = 1;
            if (keyword != null) {
                String like = "%" + keyword + "%";
                ps.setString(paramIndex++, like);
                ps.setString(paramIndex++, like);
            }
            if (limitParam > 0) {
                ps.setInt(paramIndex, limitParam);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String amenitiesStr = rs.getString("amenities");
                    String imagesStr = rs.getString("images");

                    List<String> amenities = (amenitiesStr == null || amenitiesStr.isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(amenitiesStr.split(",")));

                    List<String> images = (imagesStr == null || imagesStr.isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(imagesStr.split(",")));

                    House h = new House(
                            rs.getInt("id"),
                            rs.getInt("owner_id"),
                            null, // ownerName - can be loaded separately if needed
                            rs.getString("name"),
                            rs.getString("location"),
                            rs.getString("price"),
                            rs.getString("type"),
                            rs.getString("bedrooms"),
                            rs.getString("bathrooms"),
                            rs.getString("kitchens"),
                            rs.getString("description"),
                            amenities,
                            images,
                            rs.getString("status"),
                            rs.getTimestamp("created_at")
                    );
                    list.add(h);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Old schema query method for backward compatibility
    private List<House> queryHousesOld(String sql, String keyword) {
        return queryHousesOld(sql, keyword, -1);
    }

    private List<House> queryHousesOld(String sql, String keyword, int limitParam) {
        List<House> list = new ArrayList<>();

        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int paramIndex = 1;
            if (keyword != null) {
                String like = "%" + keyword + "%";
                ps.setString(paramIndex++, like);
                ps.setString(paramIndex++, like);
            }
            if (limitParam > 0) {
                ps.setInt(paramIndex, limitParam);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String amenitiesStr = rs.getString("amenities");
                    String imagesStr = rs.getString("images");

                    List<String> amenities = (amenitiesStr == null || amenitiesStr.isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(amenitiesStr.split(",")));

                    List<String> images = (imagesStr == null || imagesStr.isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(imagesStr.split(",")));

                    House h = new House(
                            rs.getInt("id"),
                            0, // owner_id - not in old schema
                            null, // ownerName
                            rs.getString("name"),
                            rs.getString("location"),
                            rs.getString("price"),
                            rs.getString("type"),
                            rs.getString("bedrooms"),
                            rs.getString("bathrooms"),
                            rs.getString("kitchens"),
                            rs.getString("description"),
                            amenities,
                            images,
                            "approved", // status - not in old schema, default to approved
                            null // created_at - not in old schema
                    );
                    list.add(h);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}