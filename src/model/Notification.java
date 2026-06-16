package model;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private int userId;
    private String message;
    private String type;
    private Integer relatedId;
    private boolean isRead;
    private Timestamp createdAt;

    public Notification() {}

    public Notification(int id, int userId, String message, String type, Integer relatedId, boolean isRead, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.relatedId = relatedId;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public Notification(int userId, String message, String type, Integer relatedId) {
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.relatedId = relatedId;
        this.isRead = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getRelatedId() { return relatedId; }
    public void setRelatedId(Integer relatedId) { this.relatedId = relatedId; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
