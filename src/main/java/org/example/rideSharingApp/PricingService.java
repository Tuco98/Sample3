package org.example.rideSharingApp;

public class PricingService {
    PricingStrategy pricingStrategy;
    SurgeService surgeService;

    public double getEstimate(Location src, Location dst){
        double base = pricingStrategy.calculateBaseFare(src, dst);
        double surge = surgeService.getSurgeMultiplier(src);

        return base*surge;
    }
}
