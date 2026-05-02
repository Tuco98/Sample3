package org.example.discountSystem;

public class MinCartValueCriteria implements DiscountCriteria{
    private int minCartValue;
    @Override
    public boolean isSatisfied(Cart cart) {
        return cart.getTotalAmount() >= minCartValue;
    }
}
