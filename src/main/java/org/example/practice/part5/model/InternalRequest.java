package org.example.practice.part5.model;

public class InternalRequest extends Request{
    int floor;
    int elevatorId;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    @Override
    public RequestType getType() {
        return RequestType.INTERNAL;
    }
}
