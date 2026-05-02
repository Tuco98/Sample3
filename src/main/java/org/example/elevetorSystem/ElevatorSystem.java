package org.example.elevetorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<Elevator> elevators;
    private Scheduler scheduler;

    public ElevatorSystem(int numElevators, int startFloor) {
        elevators = new ArrayList<>();
        scheduler = new Scheduler();

        for(int i=0;i<numElevators;i++){
            elevators.add(new Elevator(i,startFloor));
        }
    }

    public void handleExternalRequest(int floor, Direction direction){
        ExternalRequest request = new ExternalRequest(floor, direction);
        Elevator ele = scheduler.scheduleElevator(elevators,request);
        ele.addStop(floor);
    }

    public void stepSimulation(){
        for(Elevator e: elevators){
            e.move();
        }
    }
}
