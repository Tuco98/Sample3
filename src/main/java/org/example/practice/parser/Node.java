package org.example.practice.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String tag;
    List<Node> children;

    public Node(String tag) {
        this.tag = tag;
        this.children = new ArrayList<>();
    }
}
