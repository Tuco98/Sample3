package org.example.toggleManagementSystem;

import java.util.List;

public class FeatureFlag {
    String id;
    String name;
    FlagStatus status;
    List<Rule> rules;

    public FeatureFlag(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
