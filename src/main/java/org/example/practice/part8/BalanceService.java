package org.example.practice.part8;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {

    public static Map<String, Map<String, Double>> balance = new HashMap<>();

    public void updateBalance(String from, String to, double amt){
        //from owes to
        balance.putIfAbsent(from, new HashMap<>());
        balance.putIfAbsent(to, new HashMap<>());

        double existing = balance.get(to).getOrDefault(from, 0.0);

        if(existing != 0){

            if(existing > amt){
                balance.get(to).put(from,existing-amt);
            }else if(amt > existing){
                balance.get(from).put(to,amt-existing);
                balance.get(to).remove(from);
            }else{
                balance.get(to).remove(from);
            }

        }else{
            double curr = balance.get(from).getOrDefault(to,0.0);
            balance.get(from).put(to,curr+amt);
        }
    }
}
