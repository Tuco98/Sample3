package org.example.inMemoryKV.model;

public class Entry<V> {
    private final V value;
    private final long expiryTime;

    public Entry(V value, long ttlMillis) {
        this.value = value;
        this.expiryTime = ttlMillis>0?System.currentTimeMillis()+ttlMillis:Long.MAX_VALUE;
    }

    public V getValue() {
        return value;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public boolean isExpired(){
        return expiryTime<System.currentTimeMillis();
    }
}
