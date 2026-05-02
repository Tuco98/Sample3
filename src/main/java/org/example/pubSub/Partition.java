package org.example.pubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Partition {
    int id;
    List<Message> log = new CopyOnWriteArrayList<>();
    AtomicLong offset = new AtomicLong(0);

    public Partition(int id) {
        this.id = id;
    }

    public long append(Message message){
        long off = offset.getAndIncrement();
        message.offset = off;
        log.add(message);
        return off;
    }

    public List<Message> read(long off){
        List<Message> res = new ArrayList<>();
        for(Message m:log){
            if(m.offset>=off){
                res.add(m);
            }
        }

        return res;
    }
}
