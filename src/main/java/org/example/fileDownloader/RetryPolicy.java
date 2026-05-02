package org.example.fileDownloader;

public class RetryPolicy {
    int maxRetries;
    long backOff;

    public RetryPolicy(int maxRetries, long backOff) {
        this.maxRetries = maxRetries;
        this.backOff = backOff;
    }
}
