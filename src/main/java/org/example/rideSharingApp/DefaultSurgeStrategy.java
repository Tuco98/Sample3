package org.example.rideSharingApp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSurgeStrategy implements SurgeStrategy{
    Map<String, Integer> demand = new ConcurrentHashMap<>();
    Map<String, Integer> supply = new ConcurrentHashMap<>();

    String getZone(Location loc){
        return (loc.x/10)+"_"+(loc.y);
    }


    @Override
    public double getSurgeMultiplies(Location loc) {
        String zone = getZone(loc);

        int d = demand.getOrDefault(zone, 1);
        int s = supply.getOrDefault(zone, 1);

        int ratio = (d/s);

        if(ratio>2.0) return 2.0;
        else if(ratio>1.5) return 1.5;

        return 1;
    }
}
