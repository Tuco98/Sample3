package org.example.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    String id;
    List<Floor> floors;
    public static int semaphore = 2;
    public ParkingLot(String id, int num) {
        this.id = id;
        this.floors = new ArrayList<>(num);
    }

    public String assignParkingSpot(Car car){

         // 0, 1, 0



        if(semaphore != 0){
            semaphore--;
            // critical section

            semaphore++;

        }

//        synchronized (ParkingLot.class){
//
//            //my logic
//            //filter for AVAILABLE parking spots
//        }

        return null;

    }

    public void park(Car car, String parkingSpot) {
        ParkingSpot parkingSpot1 = new ParkingSpot();
        parkingSpot1.status = "OCCUPIED";
    }
}
