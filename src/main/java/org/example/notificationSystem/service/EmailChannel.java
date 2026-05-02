package org.example.notificationSystem.service;

import org.example.notificationSystem.model.ChannelType;
import org.example.notificationSystem.model.DeliveryResult;
import org.example.notificationSystem.model.Notification;
import org.example.notificationSystem.model.NotificationChannel;

public class EmailChannel implements NotificationChannel {
    @Override
    public ChannelType getChannelType() {
        return ChannelType.EMAIL;
    }

    @Override
    public DeliveryResult send(Notification notification, String renderedContent) {
        //email implementation
        return null;
    }
}
