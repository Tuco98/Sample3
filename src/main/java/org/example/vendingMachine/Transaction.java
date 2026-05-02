package org.example.vendingMachine;

import java.util.UUID;

public class Transaction {
    String id;
    int amountInserted;
    int productPrice;
    TransactionStatus status;

    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.status = TransactionStatus.INITIATED;
    }
}
