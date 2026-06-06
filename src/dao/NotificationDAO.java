package dao;

import model.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public List<Notification> getNotificationsForUser(int userId) {
        List<Notification> list = new ArrayList<>();

        // Hardcoded dummy data
        list.add(new Notification(1, "🏠 Booking Confirmed", "Rented Villa - 2026/1/8", false, "2026/1/8"));
        list.add(new Notification(2, "📋 Booking Request", "3BHK Flat pending approval", false, "2026/2/4"));
        list.add(new Notification(3, "❌ Booking Cancelled", "CozyHome - 2026/1/10", false, "2026/1/10"));
        list.add(new Notification(4, "💬 New Message", "Landlord replied", true, "2026/1/9"));

        // Load active admin notices from the memory store
        int id = 5;
        for (String notice : model.NoticeStore.getNotices()) {
            // Displays your custom typed message as the main bold title
            list.add(new Notification(id++, "📢 " + notice, "System Announcement", false, "2026/6/3"));
        }
        return list;
    }

    public void markAllAsRead(int userId) {
        System.out.println("Marked all as read for user: " + userId);
    }

    public void addNotice(String message) {
        System.out.println("Notice logged to console: " + message);
    }
}