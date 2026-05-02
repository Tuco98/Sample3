package org.example.fileSystem.recursive.model;

public abstract class FileNode {
    protected String name;

    public FileNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isFile();
}
