package org.example.splitwise;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    static Map<String, Map<String,Double>> balances = new HashMap<>();

    void update(String from, String to, double amt){
//        from owes to amnt

        balances.putIfAbsent(to, new HashMap<>());
        balances.putIfAbsent(from, new HashMap<>());

        double existing = balances.get(to).getOrDefault(from,0.0); //to owes from

        if(existing != 0){

            if(existing > amt){
                balances.get(to).put(from,existing-amt);
            }else if(existing<amt){
                balances.get(from).put(to,amt-existing);
                balances.get(to).remove(from);
            }else {
                balances.get(to).remove(from);
            }

        }else {
            double curr = balances.get(from).getOrDefault(to,0.0);
            balances.get(from).put(to, curr+amt);
        }


    }

    void show(){
        for(String u:balances.keySet()){
            for (Map.Entry<String, Double> e: balances.get(u).entrySet()){
                System.out.println("User "+u+" owes "+e.getKey()+" amount: "+e.getValue());
            }
        }
    }
}
