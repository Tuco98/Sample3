package org.example.pubSub;

import java.nio.charset.StandardCharsets;

public class Demo {
    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.createTopic("orders",3);
        Producer producer = new Producer("producer1",broker);
        for(int i=0;i<5;i++){
            producer.send("orders","key"+i,("message-"+i).getBytes(StandardCharsets.UTF_8));
        }

        Consumer c1 = new Consumer("c1","cg1",broker);
        Consumer c2 = new Consumer("c2","cg1",broker);

        c1.subscribe("orders");
        c2.subscribe("orders");

        c1.poll();
        c2.poll();


    }
}
