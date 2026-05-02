package org.example.pubSub;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    Map<String, Topic> topics = new ConcurrentHashMap<>();
    ConsumerGroupManager consumerGroupManager = new ConsumerGroupManager();

    public void createTopic(String topicName, int partitions){
        topics.put(topicName,new Topic(topicName,partitions));
    }

    public Topic getTopic(String topicName){
        return topics.get(topicName);
    }
}
