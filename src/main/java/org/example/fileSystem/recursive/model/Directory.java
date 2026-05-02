package org.example.fileSystem.recursive.model;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileNode{
    List<FileNode> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileNode node){
        children.add(node);
    }

    public List<FileNode> getChildren() {
        return children;
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
