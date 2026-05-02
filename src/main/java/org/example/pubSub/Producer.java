package org.example.pubSub;

public class Producer {
    String id;
    Broker broker;

    public Producer(String id, Broker broker) {
        this.id = id;
        this.broker = broker;
    }

    public void send(String topicName, String key, byte[] value){
        Topic topic = broker.getTopic(topicName);
        Partition partition = topic.getPartition(key);
        
        Message message = new Message(key,value,-1,-1);
        long offset = partition.append(message);
        System.out.println("Producer produced message to partition: "+partition+" offset: "+offset);
    }
}
