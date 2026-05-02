package org.example.discountSystem;

import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    private User user;

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalAmount(){
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
