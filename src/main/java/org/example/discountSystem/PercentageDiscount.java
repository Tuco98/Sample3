package org.example.discountSystem;

public class PercentageDiscount extends Discount{
    private double percentage;
    @Override
    public double apply(Cart cart) {
        return (cart.getTotalAmount()*percentage)/100;
    }
}
