package org.example.pubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConsumerGroupManager {
    Map<String, ConsumerGroup> groups = new ConcurrentHashMap<>();

    public synchronized void registerConsumer(String consumerGroupId,Consumer consumer, Topic topic){
        groups.putIfAbsent(consumerGroupId, new ConsumerGroup(consumerGroupId));
        ConsumerGroup group = groups.get(consumerGroupId);
        group.consumers.add(consumer);

        rebalance(group, topic);
    }

    private void rebalance(ConsumerGroup group, Topic topic) {
        List<Consumer> consumers = group.consumers;
        List<Partition> partitions = topic.partitions;

        int cSize = consumers.size();
        int pSize = partitions.size();

        for (Consumer c: consumers){
            c.assignPartitions(new ArrayList<>());
        }

        for (int i=0;i< pSize;i++){
            Consumer c = consumers.get(i%cSize);
            List<Partition> p = new ArrayList<>(c.assignedPartitions);
            p.add(partitions.get(i));
            c.assignPartitions(p);
        }

        System.out.println("Rebalance compleed for group: "+group.consumerGroupId);
    }
}
