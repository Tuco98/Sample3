package org.example.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class CashInventory {
    Map<Integer, Integer> coins = new HashMap<>();

    int[] denoms = {10,5,2,1};

    public void add(int denomination, int count){
        coins.put(denomination,coins.getOrDefault(denomination, 0)+1);
    }

    public boolean canReturnChange(int amount){
        Map<Integer, Integer> temp = new HashMap<>(coins);
        for(int d:denoms){
            while (amount>=d && temp.getOrDefault(d,0)>0){
                amount-=d;
                temp.put(d,-1);
            }
        }
        return amount == 0;
    }

    public void deductChange(int amount){
        Map<Integer, Integer> temp = new HashMap<>(coins);
        for(int d:denoms){
            while (amount>=d && temp.getOrDefault(d,0)>0){
                amount-=d;
                temp.put(d,-1);
            }
        }

    }


}
