package org.example.practice.Part6;

public class EmailChannel implements NotificationChannel{
    @Override
    public Channel getChannelType() {
        return Channel.EMAIL;
    }

    @Override
    public SendResult sendNotification(Notification notification) {
        //email implementation;
        return null;
    }
}
