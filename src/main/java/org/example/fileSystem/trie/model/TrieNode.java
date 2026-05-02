package org.example.fileSystem.trie.model;

import java.util.Map;

public class TrieNode {
    Map<String, TrieNode> children;
    boolean isFile;
    String content;

    public TrieNode(Map<String, TrieNode> children, boolean isFile, String content) {
        this.children = children;
        this.isFile = isFile;
        this.content = content;
    }

    public TrieNode() {
    }

    public Map<String, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<String, TrieNode> children) {
        this.children = children;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
