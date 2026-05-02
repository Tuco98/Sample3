package org.example.practice.part4;

public interface DBStrategy {
    void connect();
    void executeQuery(String query);
    void close();
}
