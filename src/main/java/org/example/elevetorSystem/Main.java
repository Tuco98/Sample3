package org.example.elevetorSystem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem elevatorSystem = new ElevatorSystem(2,0);
        elevatorSystem.handleExternalRequest(5,Direction.UP);
        elevatorSystem.handleExternalRequest(3,Direction.UP);

        for(int i=0;i<15;i++){
            elevatorSystem.stepSimulation();
            Thread.sleep(500);
        }
    }
}
