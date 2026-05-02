package org.example.rideSharingApp;

public class SurgeService {
    SurgeStrategy strategy = new DefaultSurgeStrategy();

    public double getSurgeMultiplier(Location loc){
        return strategy.getSurgeMultiplies(loc);
    }
}
