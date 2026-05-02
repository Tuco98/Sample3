package org.example.practice.Part6;

public interface NotificationChannel {
    Channel getChannelType();
    SendResult sendNotification(Notification notification);
}
