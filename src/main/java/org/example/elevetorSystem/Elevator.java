package org.example.elevetorSystem;
/*

Requirements:

N elevators
M floors

 */


import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator {

    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private final TreeSet<Integer> upStops = new TreeSet<>();
    private final TreeSet<Integer> downStops = new TreeSet<>(Collections.reverseOrder());

    private final ReentrantLock lock = new ReentrantLock();

    public Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void addStop(int floor){
        lock.lock();
        try {
            if(floor > currentFloor){
                upStops.add(floor);
            } else if (floor < currentFloor) {
                downStops.add(floor);
            }
        } finally {
            lock.unlock();
        }
    }

    public void move(){
        lock.lock();
        try {
            if(direction == Direction.UP || direction==Direction.IDLE){
                if(!upStops.isEmpty()){
                    direction = Direction.UP;
                    state = ElevatorState.MOVING;

                    currentFloor++;

                    if(upStops.contains(currentFloor)){
                        upStops.remove(currentFloor);
                        state = ElevatorState.STOPPED;
                    }
                } else if(!downStops.isEmpty()){
                    direction = Direction.DOWN;
                } else{
                    direction = Direction.IDLE;
                    state = ElevatorState.IDLE;
                }

            } else if( direction == Direction.DOWN){
                if(!downStops.isEmpty()){
                    direction = Direction.DOWN;
                    state = ElevatorState.MOVING;

                    currentFloor--;

                    if(downStops.contains(currentFloor)){
                        downStops.remove(currentFloor);
                        state = ElevatorState.STOPPED;
                    }
                } else if(!upStops.isEmpty()){
                    direction = Direction.UP;
                } else {
                    direction = Direction.IDLE;
                    state = ElevatorState.IDLE;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
