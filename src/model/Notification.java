package model;

public class Notification {
    private int id;
    private String title;
    private String message;
    private boolean isRead;
    private String date;

    public Notification(int id, String title, String message, boolean isRead, String date) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public boolean isRead() { return isRead; }
    public String getDate() { return date; }
    public void setRead(boolean read) { isRead = read; }
}