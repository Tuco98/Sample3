package org.example.practice.part5.model;

import java.util.Collections;
import java.util.TreeSet;

public class Elevator {
    String id;
    int currentFloor;
    TreeSet<Integer> upStops = new TreeSet<>();
    TreeSet<Integer> downStops = new TreeSet<>(Collections.reverseOrder());
    ElevatorState state;
    Direction currDirection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public TreeSet<Integer> getUpStops() {
        return upStops;
    }

    public void setUpStops(TreeSet<Integer> upStops) {
        this.upStops = upStops;
    }

    public TreeSet<Integer> getDownStops() {
        return downStops;
    }

    public void setDownStops(TreeSet<Integer> downStops) {
        this.downStops = downStops;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public Direction getCurrDirection() {
        return currDirection;
    }

    public void setCurrDirection(Direction currDirection) {
        this.currDirection = currDirection;
    }

    void moveTo(int floor){
        setCurrentFloor(floor);
    }

    void addStop(int floor){
        if(getCurrentFloor()<floor){
            upStops.add(floor);
        }else if(getCurrentFloor()>floor){
            downStops.add(floor);
        }
    }

    void move(){
        if(getCurrDirection().equals(Direction.UP) || getCurrDirection().equals(Direction.IDLE)){
            if(!upStops.isEmpty()){
                setCurrDirection(Direction.UP);
                int destFloor = upStops.first();
                setState(ElevatorState.MOVING);
                moveTo(destFloor);
                upStops.remove(destFloor);
                setState(ElevatorState.STOPPED);
            }else if(!downStops.isEmpty()){
                setCurrDirection(Direction.DOWN);
                setState(ElevatorState.IDLE);
            }else{
                setCurrDirection(Direction.IDLE);
                setState(ElevatorState.IDLE);
            }
        }else if(getCurrDirection().equals(Direction.DOWN) || getCurrDirection().equals(Direction.IDLE)){
            if(!downStops.isEmpty()){
                setCurrDirection(Direction.DOWN);
                int destFloor = downStops.first();
                setState(ElevatorState.MOVING);
                moveTo(destFloor);
                downStops.remove(destFloor);
                setState(ElevatorState.STOPPED);
            }else if(!upStops.isEmpty()){
                setCurrDirection(Direction.UP);
                setState(ElevatorState.IDLE);
            }else{
                setCurrDirection(Direction.IDLE);
                setState(ElevatorState.IDLE);
            }
        }
    }

    public boolean isIdle(){
        return getState().equals(ElevatorState.IDLE);
    }
}
