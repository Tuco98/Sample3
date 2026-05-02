package org.example.vendingMachine;

public class HasMoneyState implements MachineState{
    @Override
    public void insertMoney(VendingMachine vm, int amount) {
        vm.currentBalance+=amount;
        vm.cashInventory.add(amount,1);
    }

    @Override
    public void selectProduct(VendingMachine vm, String slotId) {
        Slot slot = vm.slotMap.get(slotId);
        if (slot == null || !slot.isAvailable()){
            throw new RuntimeException("Out of stock");
        }
        int price = slot.product.price;
        if(vm.currentBalance<price){
            throw new RuntimeException("Insufficient money");
        }
        vm.selectedSlot = slot;
        vm.state = new DispensingState();
        vm.selectedSlot.dispense();
    }

    @Override
    public void dispense(VendingMachine vm) {
        throw new RuntimeException("Select product first");
    }

    @Override
    public void refund(VendingMachine vm) {
        int refund = vm.currentBalance;
        vm.currentBalance = 0;
        vm.state = new IdleState();
        System.out.println("Refunded: "+refund);
    }
}
