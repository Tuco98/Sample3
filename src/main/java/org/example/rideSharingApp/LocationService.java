package org.example.rideSharingApp;

import java.util.HashMap;
import java.util.Map;

public class LocationService {
    public static Map<String, Location> driverLocationmap = new HashMap<>(); //driverId->driverLocation

    public void updateLocation(String driverId, Location loc){
        driverLocationmap.put(driverId,loc);
    }

    public Location getLocation(String driverId){
        return driverLocationmap.get(driverId);
    }
}
