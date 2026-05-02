package org.example.practice.Part6;

import java.util.List;

public class NotificationService {
    NotificationProcessor processor;

    void handleEvent(Notification notification, List<Channel> channels){

        for (Channel c: channels){
            NotificationDelivery delivery = new NotificationDelivery();
            // properties
            delivery.channel = c;

            processor.process(delivery, notification);
        }

    }


}
