package org.example.parkingLot;

public class Car {
    String id;

    synchronized void f(){

    }

    void f2(){
        synchronized (Car.class){
            // code
        }
    }

    public static void main(String[] args) {
        Car a = new Car();
        Car b = new Car();

        a.f();

        b.f();
    }
}




