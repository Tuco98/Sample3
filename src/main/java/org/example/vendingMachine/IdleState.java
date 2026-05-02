package org.example.vendingMachine;

public class IdleState implements MachineState{
    @Override
    public void insertMoney(VendingMachine vm, int amount) {
        vm.currentBalance+=amount;
        vm.cashInventory.add(amount,1);
        vm.state = new HasMoneyState();
    }

    @Override
    public void selectProduct(VendingMachine vm, String slotId) {
        throw new RuntimeException("Insert money first");
    }

    @Override
    public void dispense(VendingMachine vm) {
        throw new RuntimeException("Initiate Purchase first");
    }

    @Override
    public void refund(VendingMachine vm) {
        throw new RuntimeException("No money to refund");
    }
}
