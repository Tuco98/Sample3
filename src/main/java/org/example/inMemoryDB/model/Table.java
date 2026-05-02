package org.example.inMemoryDB.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Table {
    private final String name;
    private final Schema schema;

    private final ConcurrentHashMap<Object, Row> rows = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<
            String, //column
            ConcurrentHashMap<Object,Set<Object>> //column values -> set of primarykeys
            > index = new ConcurrentHashMap<>();

    public Table(String name, Schema schema) {
        this.name = name;
        this.schema = schema;
    }

    public void createIndex(String column){
        if(!index.containsKey(column)){
            index.put(column, new ConcurrentHashMap<>());
        }

        //backfill logic
        for(Row row: rows.values()){
            Object columnValue = row.get(column);
            Object pk = row.get(schema.getPrimaryKey());

            index.get(column)
                    .computeIfAbsent(columnValue,k->ConcurrentHashMap.newKeySet())
                    .add(pk);
        }
    }

    public void insert(Row row){
        Object pk = row.get(schema.getPrimaryKey());
        if(pk == null){
            throw new IllegalArgumentException("PK cannot be null");
        }
        rows.putIfAbsent(pk,row);

        //secondary index

        for(String col: index.keySet()){
            Object val = row.get(col);

            index.get(col)
                    .computeIfAbsent(val, k->ConcurrentHashMap.newKeySet())
                    .add(pk);
        }
    }

    public List<Row> query (String column, Object value){
        if(!index.containsKey(column)){
            List<Row> res = new ArrayList<>();
            for(Row r: rows.values()){
                if(value.equals(r.get(column))){
                    res.add(r);
                }
            }
            return res;
        }

        List<Row> res = new ArrayList<>();
        Set<Object> pks = index.get(column).get(value);

        for(Object p:pks){
            res.add(rows.get(p));
        }
        return res;
    }

    public Row get(Object primaryKey){
        return rows.get(primaryKey);
    }

    public void delete(Object primaryKey){
        Row remove = rows.remove(primaryKey);
        if(remove == null) return;

        for(String column: index.keySet()){
            Object value = remove.get(column);

            Set<Object> pks = index.get(column).get(value);

            if(pks != null){
                pks.remove(primaryKey);
                if(pks.isEmpty()){
                    index.get(column).remove(value);
                }
            }

        }
    }

    public List<Row> getAll(){
        return new ArrayList<>(rows.values());
    }

    public void update(Object primaryKey, String column, Object newValue ){
        Row row = rows.get(primaryKey);
        if(row == null) return;

        Object oldValue = row.get(column);
        row.put(column,newValue);

        if(index.containsKey(column)){
            Set<Object> pks = index.get(column).get(oldValue);
            if(pks != null){
                pks.remove(primaryKey);
            }

            index.get(column).computeIfAbsent(newValue, k->ConcurrentHashMap.newKeySet()).add(primaryKey);
        }
    }


}
