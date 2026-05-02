package org.example.discountSystem;

public class BuyXGetY extends Discount{
    private String productId;
    private int buyQty;
    private int freeQty;
    @Override
    public double apply(Cart cart) {

        int quantity = cart.getCartItems().stream().filter(c -> c.getProductId().equals(productId)).mapToInt(CartItem::getQuantity).sum();

        int freeItems = (quantity/buyQty)*freeQty;

        double price = cart.getCartItems().stream().filter(c -> c.getProductId().equals(productId)).findFirst().map(CartItem::getUnitPrice).orElse(0.0);

        return freeItems*price;
    }
}
