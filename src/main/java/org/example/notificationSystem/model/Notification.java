package org.example.notificationSystem.model;

import java.util.Map;

public class Notification {
    private String id;
    private String userId;
    private NotificationType type;
    private Map<String, Object> payload;
    private NotificationStatus status;

    public Notification(String id, String userId, NotificationType type, Map<String, Object> payload, NotificationStatus status) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.payload = payload;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationType getType() {
        return type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public NotificationStatus getStatus() {
        return status;
    }
}
