package org.example.notificationSystem.service;

import org.example.notificationSystem.model.DeliveryResult;
import org.example.notificationSystem.model.Notification;
import org.example.notificationSystem.model.NotificationChannel;

public class DeliveryManager {
    //NotifcationRepository notiRepo;

    private final int maxRetries = 3;

    public void deliver(Notification notification, NotificationChannel notificationChannel,String content){
        int retry = 0;

        while (retry<maxRetries){
            DeliveryResult res = notificationChannel.send(notification,content);
            if(res.isSuccess()){
                //repo.markSent(notifcationId,channelType);
                return;
            }
            retry++;
        }

        //repi.markFailed();
    }
}
