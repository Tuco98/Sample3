package org.example.movieBookingSystem;

import java.util.Calendar;

public class WeekendPricing implements PricingStrategy{
    @Override
    public double getPrice(ShowSeat seat, Show show) {
        if(show.date.getDay()== Calendar.SATURDAY || show.date.getDay()==Calendar.SUNDAY){
            return seat.basePrice*1.2;
        }

        return seat.basePrice;
    }
}
