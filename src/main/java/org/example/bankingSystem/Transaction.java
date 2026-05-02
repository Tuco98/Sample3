package org.example.bankingSystem;

import java.util.UUID;

public class Transaction {
    private String transactionId;
    private String fromId;
    private String toId;
    private long timestamp;
    private double amount;
    private String status;

    public Transaction(String fromId, String toId, long timestamp, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.fromId = fromId;
        this.toId = toId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.status = "PENDING";
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
