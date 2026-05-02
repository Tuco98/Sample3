package org.example.practice.part4;

public class MySQLDBStrategy implements DBStrategy{

    @Override
    public void connect() {
        System.out.println("Connecting to MySQL db");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing mysql query: "+query);
    }

    @Override
    public void close() {
        System.out.println("Closing mysql connection");
    }
}
