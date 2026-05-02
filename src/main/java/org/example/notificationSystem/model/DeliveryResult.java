package org.example.notificationSystem.model;

public class DeliveryResult {
    private final boolean success;
    private final String message;

    public DeliveryResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public  boolean isSuccess(){
        return success;
    }
}
