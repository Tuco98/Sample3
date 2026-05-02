package org.example.inMemoryDB.service;

import org.example.inMemoryDB.model.*;

import java.util.Arrays;
import java.util.List;

public class MainDriver {
    public static void main(String[] args) {
        Database db = new Database();

        Schema userSchema = new Schema(
                Arrays.asList(
                        new Column("id", Integer.class),
                        new Column("name", String.class),
                        new Column("age", Integer.class)
                ),
                "id"
        );

        db.createTable("users", userSchema);

        Table users = db.getTable("users");

        users.createIndex("age");

        Row r1 = new Row();
        r1.put("id", 1);
        r1.put("name", "Alice");
        r1.put("age", 25);

        Row r2 = new Row();
        r2.put("id", 2);
        r2.put("name", "Bob");
        r2.put("age", 25);

        users.insert(r1);
        users.insert(r2);

        List<Row> result = users.query("age", 25);

        System.out.println("Users with age 25:");
        for (Row r : result) {
            System.out.println(r.getAll());
        }

    }
}
