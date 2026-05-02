package org.example.movieBookingSystem;

public class BasePrice implements PricingStrategy{

    @Override
    public double getPrice(ShowSeat seat, Show show) {
        return seat.basePrice;
    }
}
