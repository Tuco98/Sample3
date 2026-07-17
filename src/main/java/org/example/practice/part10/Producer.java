package org.example.practice.part10;

import java.util.UUID;

public class Producer {
    Broker broker;

    public Producer(Broker broker) {
        this.broker = broker;
    }

    public void sendMessage(String topicId, String message){
        Topic topic = broker.getTopic(topicId);
        if (topic == null){
            System.out.println("Invalid topicname");
            return;
        }
        Partition partitionForRouting = topic.getPartitionForRouting();
        partitionForRouting.append(new Message(topicId+ UUID.randomUUID().toString(),message));
    }
}
