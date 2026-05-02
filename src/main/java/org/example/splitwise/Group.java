package org.example.splitwise;

import java.util.List;

public class Group {
    String id;
    String name;
    List<User> members;

    public Group(String id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }
}
