package org.example.discountSystem;

import java.util.List;

public class DiscountEngine {
    private List<Discount> availableDiscounts;

    public DiscountEngine(List<Discount> availableDiscounts) {
        this.availableDiscounts = availableDiscounts;
    }

    public double calculateBestDiscount(Cart cart){
        double maxDiscount = 0;

        for (Discount discount: availableDiscounts){
            if(discount.isApplicable(cart)){
                double amt = discount.apply(cart);
                maxDiscount = Math.max(amt, maxDiscount);
            }
        }

        return maxDiscount;
    }
}
