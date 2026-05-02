package org.example.rideSharingApp;

public class DefaultBaseFare implements PricingStrategy{
    @Override
    public double calculateBaseFare(Location src, Location dst) {
        double dist = src.dist(dst);
        double base = 50;
        double perKm = 12;

        return base+(dist*perKm);
    }
}
