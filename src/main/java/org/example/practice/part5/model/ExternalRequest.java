package org.example.practice.part5.model;

public class ExternalRequest extends Request{
    int destFloor;
    int srcFloor;

    public int getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(int destFloor) {
        this.destFloor = destFloor;
    }

    public int getSrcFloor() {
        return srcFloor;
    }

    public void setSrcFloor(int srcFloor) {
        this.srcFloor = srcFloor;
    }

    public RequestType getType() {
        return RequestType.EXTERNAL;
    }
}
