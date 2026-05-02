package org.example.elevetorSystem;

import java.util.List;

public class Scheduler {
    public Elevator scheduleElevator(List<Elevator> elevators, ExternalRequest request){
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            int distance =
                    Math.abs(elevator.getCurrentFloor() - request.getFloor());

            if (distance < minDistance) {
                minDistance = distance;
                best = elevator;
            }
        }

        return best;
    }
}
