package org.example.practice.part10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {
    String id;
    List<Partition> partitions;
    AtomicInteger roundRobinIndex;

    public Topic(String id, int numPartitions) {
        this.id = id;
        this.partitions = new ArrayList<>();
        for(int i=0;i<numPartitions;i++){
            partitions.add(new Partition(id+"_partition_"+i));
        }
        this.roundRobinIndex = new AtomicInteger(0);
    }

    public Partition getPartitionForRouting(){
        int next = roundRobinIndex.getAndIncrement()%partitions.size();
        return partitions.get(next);
    }

    public String getId() {
        return id;
    }
}
