package org.example.movieBookingSystem;

import java.util.List;

public class SeatLockingService {

    ShowService showService = new ShowService();
    PricingStrategy pricingStrategy;


    public boolean lockSeats(String showId,List<String> seatIds){
        Show show = showService.getShow(showId);
        synchronized (show){
            for (String id:seatIds){
                if(show.seatMap.get(id).status != SeatStatus.AVAILABLE){
                   return false;
                }
            }

            for(String id: seatIds){
                ShowSeat seat = show.seatMap.get(id);
                seat.status = SeatStatus.LOCKED;
                seat.lockedPrice = pricingStrategy.getPrice(seat, show);
                seat.ttlExpiry = System.currentTimeMillis()+5*60*1000;
            }
            return true;
        }
    }
}
