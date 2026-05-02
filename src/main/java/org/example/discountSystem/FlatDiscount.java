package org.example.discountSystem;

public class FlatDiscount extends Discount{
    private double flatAmount;
    @Override
    public double apply(Cart cart) {
        return flatAmount;
    }
}
