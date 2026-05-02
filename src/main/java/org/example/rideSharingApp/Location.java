package org.example.rideSharingApp;

public class Location {
    int x,y;

    public double dist(Location o){
        int dx = o.x - x;
        int dy = o.y-y;
        return Math.sqrt((dx*dx)-(dy*dy));
    }
}
