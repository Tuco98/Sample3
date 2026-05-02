package org.example.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("abc",5);

        Car car = new Car();

        Car car2 = new Car();

        List<Car> cars = new ArrayList<>();

        for( Car car1: cars){
            String parkingSpot = parkingLot.assignParkingSpot(car1);

            parkingLot.park(car1,parkingSpot);
        }


        List<Car> leaving = new ArrayList<>();

    }

}
