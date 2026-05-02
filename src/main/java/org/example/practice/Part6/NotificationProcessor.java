package org.example.practice.Part6;

public class NotificationProcessor {

    ChannelFactory channelFactory;
    IdempotencyStore idempotencyStore;

    public void process(NotificationDelivery notificationDelivery, Notification notification){
        if(idempotencyStore.isProcessed(notificationDelivery.idempotencyKey)){
            return;
        }


        NotificationChannel channel = channelFactory.getChannel(notificationDelivery.channel);

        SendResult sendResult = channel.sendNotification(notification);

        if(sendResult.status){
            notificationDelivery.status = DeliveryStatus.SUCCESS;
            idempotencyStore.markProcessed(notificationDelivery.idempotencyKey);
        }else{
            notificationDelivery.status = DeliveryStatus.FAILED;
            notificationDelivery.lastAttemptTime = System.currentTimeMillis();
            RetryService.scheduleRetry(notificationDelivery);
        }
    }
}
