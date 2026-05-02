package org.example.discountSystem;

public class UserTypeCriteria implements DiscountCriteria{
    private String userType;
    @Override
    public boolean isSatisfied(Cart cart) {
        return cart.getUser().getUserType().equals(userType);
    }
}
