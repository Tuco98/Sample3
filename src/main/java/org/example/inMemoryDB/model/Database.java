package org.example.inMemoryDB.model;

import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private final ConcurrentHashMap<String, Table> tables = new ConcurrentHashMap<>();

    public void createTable(String name, Schema schema){
        tables.putIfAbsent(name,new Table(name,schema));
    }

    public void dropTable(String name){
        tables.remove(name);
    }

    public Table getTable(String name){
        return tables.get(name);
    }
}
