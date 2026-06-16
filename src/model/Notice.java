package model;

import java.sql.Timestamp;

public class Notice {
    private int id;
    private String title;
    private String message;
    private Timestamp createdAt;

    public Notice() {}

    public Notice(int id, String title, String message, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Notice(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}