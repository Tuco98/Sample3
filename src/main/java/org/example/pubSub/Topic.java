package org.example.pubSub;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    String name;
    List<Partition> partitions;

    public Topic(String name, int numPartitions) {
        this.name = name;
        this.partitions = new ArrayList<>();
        for(int i=0;i<numPartitions;i++){
            partitions.add(new Partition(i));
        }
    }

    public Partition getPartition(String key){
        int idx = Math.abs(key.hashCode() % partitions.size());
        return partitions.get(idx);
    }
}
