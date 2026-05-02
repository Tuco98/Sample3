package org.example.rideSharingApp;

import java.util.List;
import java.util.UUID;

public class RideService {
    MatchingService matchingService;
    PricingService pricingService;
    List<Driver> drivers;
    PaymentService paymentService;


    Ride requestRide(User user,Location src, Location dsr){

        double estFare = pricingService.getEstimate(src,dsr);

        RideRequest request = new RideRequest();
        request.userId = user.userId;
        request.id = UUID.randomUUID().toString();
        request.src = src;
        request.dst = dsr;
        request.status = RideStatus.REQUESTED;

        Driver d = matchingService.assignWithRetry(src,drivers);
        if(d==null){
            return null;
        }

        Ride ride = new Ride();
        ride.rideReuqestId = request.id;
        //all details;

        return ride;
    }

    void startRide(Ride ride){
        ride.status = RideStatus.STARTED;
        ride.startTime = System.currentTimeMillis();
        ride.driverId.status = DriverStatus.ON_TRIP;
    }

    void completeRide(Ride ride){
        ride.status = RideStatus.COMPLETED;
        ride.endTime = System.currentTimeMillis();

        paymentService.processPayment();

        synchronized (ride.driverId){
            ride.driverId.status = DriverStatus.AVAILABLE;
        }

    }

    void cancelRide(Ride ride){
        ride.status = RideStatus.CANCELLED;
        synchronized (ride.driverId){
            ride.driverId.status = DriverStatus.AVAILABLE;
        }
    }
}
