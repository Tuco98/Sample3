package org.example.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineService {
    Map<String,VendingMachine> vendingMachineMap = new HashMap<>();

    public void insertMoney(String id, int amount){
        VendingMachine vendingMachine = vendingMachineMap.get(id);
        vendingMachine.state.insertMoney(vendingMachine,amount);
    }

    public void selectProduct(String id,String slotId){
        VendingMachine vendingMachine = vendingMachineMap.get(id);
        vendingMachine.state.selectProduct(vendingMachine, slotId);
    }

    public void dispense(String id){
        VendingMachine vendingMachine = vendingMachineMap.get(id);
        vendingMachine.state.dispense(vendingMachine);
    }

    public void refund(String id){
        VendingMachine vendingMachine = vendingMachineMap.get(id);
        vendingMachine.state.refund(vendingMachine);
    }
}
