package org.example.vendingMachine;

public class DispensingState implements MachineState{
    @Override
    public void insertMoney(VendingMachine vm, int amount) {
        throw new RuntimeException("Machine is dispensing order now, Wait");
    }

    @Override
    public void selectProduct(VendingMachine vm, String slotId) {
        throw new RuntimeException("Already dispensing, wait");
    }

    @Override
    public void dispense(VendingMachine vm) {
        Slot slot = vm.selectedSlot;
        slot.dispense();
        vm.currentBalance-=slot.product.price;
        int change = vm.currentBalance;
        if(change>0){
            if(vm.cashInventory.canReturnChange(change)){
                vm.cashInventory.deductChange(change);
                System.out.println("Returned change: "+change);
            }else {
                throw new RuntimeException("Cannot return change");
            }
        }

        vm.currentBalance = 0;
        vm.selectedSlot = null;
        vm.state = new IdleState();
    }

    @Override
    public void refund(VendingMachine vm) {
        throw new RuntimeException("Cannot refund during dispensing");
    }
}
