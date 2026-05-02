package org.example.rateLimiter.main;

import org.example.rateLimiter.model.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(
                5,   // capacity
                2    // refill 2 tokens per second
        );

        String clientId = "user-123";

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                boolean allowed = rateLimiter.allowRequest(clientId);

                System.out.println(
                        Thread.currentThread().getName()
                                + " -> Request " + i
                                + " allowed: " + allowed
                );

                try {
                    Thread.sleep(200); // simulate traffic
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            executor.submit(task);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
