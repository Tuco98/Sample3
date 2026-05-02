package org.example.practice.part1;

import java.util.*;

public class InMemoryImpl implements InMemoryInterface{

    private final Map<String, Map<String, TreeMap<Integer, FieldEntry>>> db = new HashMap<>() ;


    @Override
    public void set(int timestamp, String key, String field, String value) {

        db.computeIfAbsent(key, k->new HashMap<>())
                .computeIfAbsent(field,f->new TreeMap<>())
                .put(timestamp,new FieldEntry(value));

    }

    @Override
    public boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue) {

        String value = get(timestamp, key, field);

        if(String.valueOf(expectedValue).equals(value)){
            set(timestamp,key,field,String.valueOf(newValue));
            return true;
        }
        return false;
    }

    @Override
    public boolean compareAndDelete(int timestamp, String key, String field, int expectedValue) {

        String value = get(timestamp, key, field);
        if(String.valueOf(expectedValue).equals(value)){
            db.get(key).get(field).put(timestamp,new FieldEntry(null));
            return true;
        }
        return false;
    }


    @Override
    public List<String> scan(int timestamp, String key) {
        Map<String, TreeMap<Integer, FieldEntry>> keyMap = db.get(key);
        if(keyMap == null) return new ArrayList<>();

        List<String> list = new ArrayList<>();

        for(String field: keyMap.keySet()){
            String value = get(timestamp, key, field);
            if(value!=null){
                list.add(field+"="+value);
            }
        }
        return list;
    }

    @Override
    public List<String> scanByPrefix(int timestamp, String key, String prefix) {
        Map<String, TreeMap<Integer, FieldEntry>> keyMap = db.get(key);
        if(keyMap == null) return new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(String field: keyMap.keySet()){
            String value = get(timestamp, key, field);
            if(field.startsWith(prefix) && value!=null){
                list.add(field+"="+value);            }
        }

        return list;
    }

    @Override
    public void setWithTTL(int timestamp, String key, String field, String value, int ttl) {

        int exp = timestamp+ttl;

        db.computeIfAbsent(key, k->new HashMap<>())
                .computeIfAbsent(field,f->new TreeMap<>())
                .put(timestamp,new FieldEntry(value,exp));


    }

    @Override
    public boolean compareAndSetWithTTL(int timestamp, String key, String field, int expectedValue, int newValue, int ttl) {

        String value = get(timestamp, key, field);

        if (value.equals(String.valueOf(expectedValue))){
            int exp = timestamp+ttl;
            setWithTTL(timestamp,key,field, String.valueOf(newValue),exp);
            return true;
        }

        return false;
    }

    @Override
    public String get(int timestamp, String key, String field){
        Map<String, TreeMap<Integer, FieldEntry>> keyMap = db.get(key);
        if(keyMap == null) return null;

        TreeMap<Integer, FieldEntry> fieldMap = keyMap.get(field);

        if(fieldMap == null) return null;

        FieldEntry value = fieldMap.floorEntry(timestamp).getValue();

        if(value.getExpTime()!= -1 && timestamp>=value.getExpTime()){
            return null;
        }

        return value.getValue();

    }
}
