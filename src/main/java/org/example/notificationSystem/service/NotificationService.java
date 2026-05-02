package org.example.notificationSystem.service;


import org.example.notificationSystem.model.*;

public class NotificationService {
    ChannelFactory channelFactory;
    DeliveryManager deliveryManager;

    public void sendNotification(Notification notification){
        //repo.save(notification)

        UserPreference pref = null; //preferenceService.get(notification.userId);

        for(ChannelType ch:ChannelType.values()){

            if(!pref.isEnabled(ch)){
                continue;
            }

            NotificationChannel channel = channelFactory.getChannel(ch);

            deliveryManager.deliver(notification, channel, "");

        }
    }
}
