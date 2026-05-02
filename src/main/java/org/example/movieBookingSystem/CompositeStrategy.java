package org.example.movieBookingSystem;

import java.util.List;

public class CompositeStrategy implements PricingStrategy{
    List<PricingStrategy> strategies;
    @Override
    public double getPrice(ShowSeat seat, Show show) {
        double price = 0.0;

        for(PricingStrategy p:strategies){
            price = p.getPrice(seat,show);
        }

        return price;
    }
}
