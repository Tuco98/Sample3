package org.example.practice.part4;

public class OracleDBStrategy implements DBStrategy{
    @Override
    public void connect() {
        System.out.println("Connecting to OracleDB");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing oracle query: "+query);
    }

    @Override
    public void close() {
        System.out.println("Closing Oracle Connection");
    }
}
