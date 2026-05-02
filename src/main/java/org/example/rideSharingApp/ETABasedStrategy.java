package org.example.rideSharingApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ETABasedStrategy implements MatchingStrategy{
    @Override
    public List<Driver> getCadidates(Location src, List<Driver> drivers, LocationService ls) {

        List<Driver> list = new ArrayList<>();

        for (Driver d: drivers){
            if(d.status.equals(DriverStatus.AVAILABLE)){
                list.add(d);
            }
        }

        Collections.sort(list, Comparator.comparingDouble(d->{
            Location location = ls.getLocation(d.driverId);
            return (location == null)?Double.MAX_VALUE:location.dist(src);
        }));


        return list;
    }
}
