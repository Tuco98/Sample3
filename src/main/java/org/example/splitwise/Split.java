package org.example.splitwise;

public abstract class Split {
    String userId;
    double amount;

    public Split(String userId) {
        this.userId = userId;
    }
}
