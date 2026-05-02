package org.example.movieBookingSystem;

public class SeatExpiryJob {
    public void releaseExpLocks(Show show){
        long now = System.currentTimeMillis();
        for(ShowSeat seat: show.seatMap.values()){
            if(seat.status==SeatStatus.LOCKED && seat.ttlExpiry > now){
                seat.status = SeatStatus.AVAILABLE;
            }
        }
    }
}
