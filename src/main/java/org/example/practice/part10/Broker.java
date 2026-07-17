package org.example.practice.part10;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    Map<String, Topic> topicMap ;

    public Broker() {
        this.topicMap = new ConcurrentHashMap<>();
    }

    public void createTopic(String topicId, int num){
        topicMap.put(topicId, new Topic(topicId, num));
    }

    public Topic getTopic(String topicId){
        return topicMap.get(topicId);
    }
}
