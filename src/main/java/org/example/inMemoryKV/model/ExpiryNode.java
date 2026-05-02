package org.example.inMemoryKV.model;

public class ExpiryNode <K>{
    private final K key;
    private final long expiryTime;

    public ExpiryNode(K key, long expiryTime) {
        this.key = key;
        this.expiryTime = expiryTime;
    }

    public K getKey() {
        return key;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
