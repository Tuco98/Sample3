package org.example.practice.part1;

import java.util.List;

public interface InMemoryInterface {
//    Level 1 : Support basic operations to manipulate records, fields, and values within fields.
//            Level 2: Support displaying a record's fields based on a filter.
//    Level 3: Support TTL settings for records.
//            Level 4: Support Look-back operations to retrieve values stored at a specific timestamp in the past.
//
//    You need to complete each level to unlock/prceed to the next level.
//
//    Interface Skeleton:

    void set(int timestamp, String key, String field, String value);

    boolean compareAndSet(int timestamp, String key, String field, int expectedValue, int newValue);
    boolean compareAndDelete(int timestamp, String key, String field, int expectedValue);
    String get(int timestamp, String key, String field) ;

    List scan(int timestamp, String key) ;
    List scanByPrefix(int timestamp, String key, String prefix) ;

    void setWithTTL(int timestamp, String key, String field, String value, int ttl) ;
    boolean compareAndSetWithTTL(int timestamp, String key, String field, int expectedValue, int newValue, int ttl) ;
}
