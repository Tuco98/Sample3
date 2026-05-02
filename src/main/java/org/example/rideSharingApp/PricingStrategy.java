package org.example.rideSharingApp;

public interface PricingStrategy {
    double calculateBaseFare(Location src, Location dst);
}
