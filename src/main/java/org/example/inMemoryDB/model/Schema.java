package org.example.inMemoryDB.model;

import java.util.List;

public class Schema {
    private final List<Column> columns;
    private final String primaryKey;


    public Schema(List<Column> columns, String primaryKey) {
        this.columns = columns;
        this.primaryKey = primaryKey;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }
}
