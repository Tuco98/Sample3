package org.example.rateLimiter.model;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {
    private final long capacity;
    private final double refillRatePerNano;

    private double currentTokens;
    private long lastRefillTime;

    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucket(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerNano = refillRatePerSecond/1_000_000_000.0;
        this.currentTokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }

    public boolean tryConsume(){
        lock.lock();
        try {
            refill();

            if(currentTokens>=1){
                currentTokens-=1;
                return true;
            }else {
                return false;
            }
        }finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsed = now-lastRefillTime;
        double tokensToAdd = elapsed*refillRatePerNano;
        if (tokensToAdd >0){
            currentTokens = Math.min(capacity, currentTokens+tokensToAdd);
            lastRefillTime = now;
        }
    }

    public long getCapacity() {
        return capacity;
    }

    public double getRefillRatePerNano() {
        return refillRatePerNano;
    }

    public double getCurrentTokens() {
        return currentTokens;
    }

    public void setCurrentTokens(double currentTokens) {
        this.currentTokens = currentTokens;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public void setLastRefillTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }
}
