package org.example.practice.part11;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private SubscriptionManager subscriptionManager;
    private List<OrderEvent> eventStore;
    private ExecutorService executorService;

    public NotificationService(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
        this.eventStore = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void processEvent(OrderEvent orderEvent){
        eventStore.add(orderEvent);
        EventType eventType = orderEvent.getEventType();
        String customerId = orderEvent.getOrder().getCustomerId();
        String sellerid = orderEvent.getOrder().getSellerId();
        String deliveryPartnerId = orderEvent.getOrder().getDeliveryPartnerId();

        switch (eventType){
            case ORDER_CREATED:
                notifyStakeHolder(customerId, orderEvent, false);
                notifyStakeHolder(sellerid, orderEvent, false);
                break;
            case ORDER_SHIPPER:
                notifyStakeHolder(customerId, orderEvent, false);
                notifyStakeHolder(deliveryPartnerId, orderEvent, false);
                break;
            case ORDER_DELIVERED:
                notifyStakeHolder(customerId, orderEvent, false);

        }
    }

    private void notifyStakeHolder(String stakeHolderId, OrderEvent orderEvent, boolean b) {
        Set<Channel> subscribedChannels = subscriptionManager.getSubscribedChannels(stakeHolderId, orderEvent.getEventType());

        for (Channel c: subscribedChannels){
            System.out.println("Notifications sent for "+stakeHolderId+" by channel "+c.toString());
        }
    }

    public void replayEvent(String orderId, EventType eventType, String stakeHolderId){
        OrderEvent event = eventStore.stream().filter(e -> e.getOrder().getOrderId().equals(orderId) && e.getEventType().equals(eventType))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("not found"));

        notifyStakeHolder(stakeHolderId, event, true);
    }

    public void shutdown(){
        executorService.shutdown();
    }
}
