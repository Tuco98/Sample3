package org.example.stockExchange;

public class Order {
    private String orderId;
    private String userId;
    private String stockId;
    private Double price;
    private int qty;
    private long timestamp;
    private OrderType orderType;

    public Order(String orderId, String userId, String stockId, Double price, int qty, long timestamp, OrderType orderType) {
        this.orderId = orderId;
        this.userId = userId;
        this.stockId = stockId;
        this.price = price;
        this.qty = qty;
        this.timestamp = timestamp;
        this.orderType = orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
