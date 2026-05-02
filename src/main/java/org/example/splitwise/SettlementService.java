package org.example.splitwise;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SettlementService {
    static class Pair{
        String user;
        Double amt;

        public Pair(String user, Double amt) {
            this.user = user;
            this.amt = amt;
        }
    }

    void settle (Map<String, Map<String,Double>> bal){
        Map<String, Double> net = new HashMap<>();

        for (String u:bal.keySet()){
            net.putIfAbsent(u, 0.0);
            for (Map.Entry<String, Double> e:bal.get(u).entrySet()){
                net.put(u,net.get(u)-e.getValue());
                net.put(e.getKey(),net.getOrDefault(e.getKey(),0.0)+e.getValue());
            }
        }

        PriorityQueue<Pair> debt = new PriorityQueue<>((a,b)->Double.compare(a.amt,b.amt));
        PriorityQueue<Pair> cred = new PriorityQueue<>((a,b)->Double.compare(b.amt,a.amt));

        for (String u: net.keySet()){
            double v = net.get(u);
            if(v<0){
                debt.offer(new Pair(u,v));
            }else{
                cred.offer(new Pair(u,v));
            }
        }

        while (!debt.isEmpty() && !cred.isEmpty()){
            Pair d = debt.poll();
            Pair c = cred.poll();

            double min = Math.min(-d.amt,c.amt);

            System.out.println(d.user+" pays "+c.user+" amt: "+min);
            d.amt +=min;
            c.amt-=min;

            if (d.amt < 0) debt.add(d);
            if (c.amt > 0) cred.add(c);
        }
    }
}
