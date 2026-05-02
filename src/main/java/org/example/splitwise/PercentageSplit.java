package org.example.splitwise;

public class PercentageSplit extends Split{
    double percentage;

    public PercentageSplit(String userId, double percentage) {
        super(userId);
        this.percentage = percentage;
    }
}
