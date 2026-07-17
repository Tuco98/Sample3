package org.example.practice.part11;

import java.time.LocalDateTime;

public class OrderEvent {
    private Order order;
    private EventType eventType;
    private String message;
    private LocalDateTime timestamp;

    public OrderEvent(Order order, EventType eventType, String message, LocalDateTime timestamp) {
        this.order = order;
        this.eventType = eventType;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Order getOrder() {
        return order;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
