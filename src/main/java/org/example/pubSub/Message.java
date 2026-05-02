package org.example.pubSub;

public class Message {
    String key;
    byte[] value;
    long offset;
    long timestamp;

    public Message(String key, byte[] value, long offset, long timestamp) {
        this.key = key;
        this.value = value;
        this.offset = offset;
        this.timestamp = System.currentTimeMillis();
    }
}
