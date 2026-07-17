package org.example.practice.part11;

public class Order {
    private String orderId;
    private String customerId;
    private String sellerId;
    private String deliveryPartnerId;

    public Order(String orderId, String customerId, String sellerId, String deliveryPartnerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.deliveryPartnerId = deliveryPartnerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getDeliveryPartnerId() {
        return deliveryPartnerId;
    }
}
