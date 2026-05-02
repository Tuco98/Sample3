package org.example.splitwise;

import java.util.List;

public class Expense {
    String id;
    String paidBy;
    double amount;
    List<Split> splits;
    SplitType splitType;
    String groupId;

    public Expense(String id, String paidBy, double amount, List<Split> splits, SplitType splitType, String groupId) {
        this.id = id;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
        this.splitType = splitType;
        this.groupId = groupId;
    }
}
