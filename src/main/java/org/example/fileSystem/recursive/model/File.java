package org.example.fileSystem.recursive.model;

public class File extends FileNode{
    private String content;

    public File(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return true;
    }
}
