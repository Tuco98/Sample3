package org.example.pubSub;

import java.util.ArrayList;
import java.util.List;

public class ConsumerGroup {
    String consumerGroupId;
    List<Consumer> consumers = new ArrayList<>();

    public ConsumerGroup(String consumerGroupId) {
        this.consumerGroupId = consumerGroupId;
    }
}
