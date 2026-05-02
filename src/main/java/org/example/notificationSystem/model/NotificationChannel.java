package org.example.notificationSystem.model;

public interface NotificationChannel {
    ChannelType getChannelType();

    DeliveryResult send(Notification notification, String renderedContent);



}
