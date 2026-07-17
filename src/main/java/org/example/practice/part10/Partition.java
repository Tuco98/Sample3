package org.example.practice.part10;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    String id;
    List<Message> messages;

    public Partition(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
    }

    public synchronized void append(Message message){
        messages.add(message);
        notifyAll();
    }

    public synchronized Message read(int offset) throws InterruptedException {
        while (offset >= messages.size()){
            wait();
        }

        return messages.get(offset);
    }

    public String getId() {
        return id;
    }
}
