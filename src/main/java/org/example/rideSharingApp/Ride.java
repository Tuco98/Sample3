package org.example.rideSharingApp;

public class Ride {
    String id;
    String rideReuqestId;
    User user;
    Driver driverId;
    Location src,dst;
    RideStatus status;
    long startTime, endTime;
}
