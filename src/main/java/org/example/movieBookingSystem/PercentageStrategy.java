package org.example.movieBookingSystem;

public class PercentageStrategy implements PricingStrategy{
    @Override
    public double getPrice(ShowSeat seat, Show show) {
        long total = show.seatMap.size();
        long booked =  show.seatMap.values().stream().filter(e->e.status.equals(SeatStatus.AVAILABLE)).count();

        double ratio = booked/total;

        if(ratio >= 0.8){
            return seat.basePrice*1.5;
        }else if(ratio > 0.5){
            return seat.basePrice*1.2;
        }else{
            return seat.basePrice;
        }
    }
}
