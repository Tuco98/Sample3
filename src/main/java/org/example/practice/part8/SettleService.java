package org.example.practice.part8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.example.practice.part8.BalanceService.balance;

public class SettleService {
    static class Person{
        public Person(String name, Double amt) {
            this.name = name;
            this.amt = amt;
        }

        String name;
        Double amt;
    }
    public void settle(){
        Map<String, Double> map = new HashMap<>();

        for(String u: balance.keySet()){
            for(String v: balance.get(u).keySet()){

                double amt = balance.get(u).get(v);
                map.put(u,map.getOrDefault(u,0.0)-amt);
                map.put(v,map.getOrDefault(v,0.0)+amt);
            }
        }



        PriorityQueue<Person> debt = new PriorityQueue<>((a,b)-> Double.compare(Math.abs(b.amt),Math.abs(a.amt)));
        PriorityQueue<Person> cred = new PriorityQueue<>((a,b)-> Double.compare(b.amt,a.amt));

        for(String u: map.keySet()){
            if(map.get(u) > 0){
                cred.offer(new Person(u, map.get(u)));
            }else if(map.get(u)<0){
                debt.offer(new Person(u, map.get(u)));
            }
        }
        while (!debt.isEmpty() && !cred.isEmpty()){
            Person debitor = debt.poll();
            Person creditor = cred.poll();

            double min = Math.min(-debitor.amt,creditor.amt);

            creditor.amt -= min;
            debitor.amt +=min;

            if(creditor.amt>0){
                cred.offer(creditor);
            }

            if(debitor.amt<0){
                debt.offer(debitor);
            }
        }

    }
}
