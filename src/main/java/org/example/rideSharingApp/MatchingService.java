package org.example.rideSharingApp;

import java.util.List;

public class MatchingService {
    MatchingStrategy matchingStrategy;
    LocationService ls;

    Driver assignWithRetry(Location src, List<Driver> drivers){
        List<Driver> cadidates = matchingStrategy.getCadidates(src, drivers, ls);

        for(Driver d: cadidates){
            if (tryAssign(d)){
                boolean accepted = sendRequestToDriver(d);
                if (accepted) return d;

                synchronized (d){
                    d.status = DriverStatus.AVAILABLE;
                }
            }
        }

        return null;
    }

    private boolean sendRequestToDriver(Driver d) {
        return  true;
    }

    private boolean tryAssign(Driver d) {
        synchronized (d){
            if(!d.status.equals(DriverStatus.AVAILABLE)) return false;
            d.status = DriverStatus.ASSIGNED;
            return true;
        }
    }
}
