package org.example.inMemoryDB.model;

public class Column {
    private final String name;
    private final Class<?> type;

    public Column(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}
