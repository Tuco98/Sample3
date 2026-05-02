package org.example.movieBookingSystem;

public interface PricingStrategy {
    double getPrice(ShowSeat seat, Show show);
}
