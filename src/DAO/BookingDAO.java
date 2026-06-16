package dao;

import database.MysqlConnector;
import model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private MysqlConnector connector = new MysqlConnector();

    /** Create a new booking (status = pending by default) */
    public boolean insertBooking(int houseId, int customerId) {
        String sql = "INSERT INTO bookings (house_id, customer_id, status, booking_date) VALUES (?, ?, 'pending', CURDATE())";
        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, houseId);
            ps.setInt(2, customerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Get all bookings for a specific customer, joined with house info */
    public List<Booking> getBookingsByCustomer(int customerId) {
        String sql = "SELECT b.id, b.house_id, b.customer_id, b.status, b.booking_date, " +
                     "h.name AS house_name, h.location, h.price, h.images " +
                     "FROM bookings b " +
                     "JOIN houses h ON b.house_id = h.id " +
                     "WHERE b.customer_id = ? " +
                     "ORDER BY b.created_at DESC";
        return queryBookings(sql, customerId, null);
    }

    /** Get all bookings for a customer filtered by status */
    public List<Booking> getBookingsByCustomerAndStatus(int customerId, String status) {
        String sql = "SELECT b.id, b.house_id, b.customer_id, b.status, b.booking_date, " +
                     "h.name AS house_name, h.location, h.price, h.images " +
                     "FROM bookings b " +
                     "JOIN houses h ON b.house_id = h.id " +
                     "WHERE b.customer_id = ? AND b.status = ? " +
                     "ORDER BY b.created_at DESC";
        return queryBookings(sql, customerId, status);
    }

    /** Admin: get all bookings */
    public List<Booking> getAllBookings() {
        String sql = "SELECT b.id, b.house_id, b.customer_id, b.status, b.booking_date, " +
                     "h.name AS house_name, h.location, h.price, h.images " +
                     "FROM bookings b " +
                     "JOIN houses h ON b.house_id = h.id " +
                     "ORDER BY b.created_at DESC";
        return queryBookings(sql, -1, null);
    }

    /** Update booking status: 'pending' | 'confirmed' | 'cancelled' */
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE id = ?";
        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Cancel a booking — only allowed if current status is 'pending' */
    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'cancelled' WHERE id = ? AND status = 'pending'";
        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Get all bookings for a specific house (used by house owner to see booking requests).
     */
    public java.util.List<Booking> getBookingsByHouseId(int houseId) {
        String sql = "SELECT b.id, b.house_id, b.customer_id, b.status, b.booking_date, " +
                     "h.name AS house_name, h.location, h.price, h.images " +
                     "FROM bookings b " +
                     "JOIN houses h ON b.house_id = h.id " +
                     "WHERE b.house_id = ? " +
                     "ORDER BY b.created_at DESC";
        return queryBookings(sql, houseId, null);
    }

    private List<Booking> queryBookings(String sql, int userId, String status) {
        List<Booking> list = new ArrayList<>();
        try (Connection con = connector.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int i = 1;
            if (userId > 0) ps.setInt(i++, userId);
            if (status != null) ps.setString(i, status);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setId(rs.getInt("id"));
                    b.setHouseId(rs.getInt("house_id"));
                    b.setCustomerId(rs.getInt("customer_id"));
                    b.setStatus(rs.getString("status"));
                    b.setBookingDate(rs.getString("booking_date"));
                    b.setHouseName(rs.getString("house_name"));
                    b.setLocation(rs.getString("location"));
                    b.setPrice(rs.getString("price"));
                    b.setImagePath(rs.getString("images")); // first image (comma-separated)
                    list.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

    // NOTE: this line is appended — paste ABOVE the last closing brace of BookingDAO class
