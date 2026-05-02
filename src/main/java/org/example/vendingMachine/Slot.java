package org.example.vendingMachine;

public class Slot {
    String id;
    Product product;
    int capacity;
    int quantity;

    public Slot(String id, Product product, int capacity, int quantity) {
        this.id = id;
        this.product = product;
        this.capacity = capacity;
        this.quantity = quantity;
    }

    public boolean isAvailable(){
        return quantity>0;
    }

    public void dispense(){
        if(quantity<=0) throw new RuntimeException("Out of stock");
        quantity--;
    }
}
