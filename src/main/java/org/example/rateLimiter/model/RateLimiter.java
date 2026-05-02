package org.example.rateLimiter.model;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final long capacity;
    private final double refillRate;

    public RateLimiter(long capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    public boolean allowRequest(String clientId){
        TokenBucket bucket = buckets.computeIfAbsent(clientId,id -> new TokenBucket(capacity,refillRate));
        return bucket.tryConsume();
    }
}
