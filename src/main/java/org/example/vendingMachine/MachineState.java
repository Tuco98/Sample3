package org.example.vendingMachine;

public interface MachineState {
    void insertMoney(VendingMachine vm, int amount);
    void selectProduct(VendingMachine vm, String slotId);
    void dispense(VendingMachine vm);
    void refund(VendingMachine vm);
}
