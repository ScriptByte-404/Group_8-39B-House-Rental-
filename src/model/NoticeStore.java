package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoticeStore {
    // Thread-safe in-memory list to share notices between frames
    private static final List<String> notices = new CopyOnWriteArrayList<>();

    public static void addNotice(String message) {
        notices.add(message);
    }

    public static List<String> getNotices() {
        return notices;
    }
}