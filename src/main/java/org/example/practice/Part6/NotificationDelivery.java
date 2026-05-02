package org.example.practice.Part6;

public class NotificationDelivery {
    String id;
    String notificationId;
    Channel channel;
    DeliveryStatus status;
    int retryCount;
    long lastAttemptTime;
    String idempotencyKey;
}
