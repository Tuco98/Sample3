package org.example.practice.part5.model;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    public List<Elevator> elevators = new ArrayList<>();
    public SchedulingStrategy strategy = new NearestStartegy();

    public void handleExternalRequest(ExternalRequest r){
        Elevator elevator = strategy.assign(elevators, r);

        synchronized (elevator){
            elevator.addStop(r.srcFloor);
            elevator.addStop(r.destFloor);
        }
    }

    public void handleInternalRequest(InternalRequest r){
        Elevator e = elevators.stream().filter(elevator -> elevator.id.equals(r.elevatorId)).findFirst().get();
        e.addStop(r.floor);
    }

    public void stepAll(){
        for (Elevator e: elevators){
            e.move();
        }
    }
}
