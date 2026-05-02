package org.example.vendingMachine;

import java.util.Map;

public class VendingMachine {
    String id;
    Map<String, Slot> slotMap;
    Slot selectedSlot;
    int currentBalance;
    CashInventory cashInventory;
    MachineState state;

    public VendingMachine(String id) {
        this.id = id;
        this.state = new IdleState();
        this.cashInventory = new CashInventory();
    }
}
