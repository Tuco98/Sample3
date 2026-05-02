package org.example.pubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer {
    String consumerId;
    String groupId;
    Broker broker;
    List<Partition> assignedPartitions = new ArrayList<>();
    Map<Partition,Long> offset = new ConcurrentHashMap<>();

    public Consumer(String consumerId, String groupId, Broker broker) {
        this.consumerId = consumerId;
        this.broker = broker;
        this.groupId = groupId;
    }

    public void subscribe(String topicName){
        broker.consumerGroupManager.registerConsumer(groupId,this,broker.getTopic(topicName));
        System.out.println("registered");
    }

    public void assignPartitions(List<Partition> partitions){
        assignedPartitions = partitions;
        for (Partition p:assignedPartitions){
            offset.putIfAbsent(p,0L);
            System.out.println("Consumer "+consumerId+" assigned to partition"+p.id);
        }
    }

    public void poll(){
        for (Partition p: assignedPartitions){
            long off = offset.getOrDefault(p,0L);
            List<Message> read = p.read(off);
            for (Message m: read){
                process(m);
                offset.put(p,m.offset+1);
            }
        }

    }

    private void process(Message m) {
        System.out.println("Consumer id: "+consumerId+" processed message: "+m.toString());
    }
}
