package org.example.practice.Part6;

import java.util.HashSet;
import java.util.Set;

public class IdempotencyStore {
    public static Set<String> idempotencySet = new HashSet<>();

    public synchronized boolean isProcessed(String idempotencyKey){
        return idempotencySet.contains(idempotencyKey);
    }

    public synchronized void markProcessed(String idempotencyKey){
        idempotencySet.add(idempotencyKey);
    }
}
