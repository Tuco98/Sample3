package org.example.practice.part5.model;

import java.util.List;

public class NearestStartegy implements SchedulingStrategy{
    @Override
    public Elevator assign(List<Elevator> elevatorList, Request request) {
        ExternalRequest externalRequest = (ExternalRequest) request;



        int minDist = Integer.MAX_VALUE;
        Elevator best = null;
        Direction d = (externalRequest.srcFloor < externalRequest.destFloor)?Direction.UP:Direction.DOWN;

        for(Elevator e: elevatorList){
            int dist = Math.abs(e.getCurrentFloor()- externalRequest.getSrcFloor());
            if(e.getCurrDirection().equals(Direction.IDLE)){
                if(minDist > dist){
                    best = e;
                    minDist = dist;
                }
            }else if(e.getCurrDirection().equals(Direction.UP) && externalRequest.srcFloor <externalRequest.destFloor){
                if(minDist > dist){
                    best = e;
                    minDist = dist;
                }
            }else if(e.getCurrDirection().equals(Direction.DOWN) && externalRequest.srcFloor > externalRequest.destFloor){
                if(minDist > dist){
                    best = e;
                    minDist = dist;
                }
            }
        }

        if(best == null){
            for (Elevator e: elevatorList){
                int dist = Math.abs(e.getCurrentFloor()- externalRequest.getSrcFloor());
                if(minDist > dist){
                    best = e;
                    minDist = dist;
                }
            }
        }

        return best;


    }
}
