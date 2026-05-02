package org.example.practice.Part6;

public class RetryService {

    public static void scheduleRetry(NotificationDelivery notificationDelivery) {

        if(notificationDelivery.retryCount >= 3){
            moveToDLQ(notificationDelivery);
            return;
        }

        notificationDelivery.retryCount++;
        notificationDelivery.status = DeliveryStatus.RETRYING;

        queueRetry(notificationDelivery);
    }

    private static void queueRetry(NotificationDelivery notificationDelivery) {

    }

    private static void moveToDLQ(NotificationDelivery notificationDelivery) {
    }
}
