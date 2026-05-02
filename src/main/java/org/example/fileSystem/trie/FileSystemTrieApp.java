package org.example.fileSystem.trie;

// Practicing Leetcode 588: Design an in memory file system
//What We Need to Support
//
//Typical in-memory FS requirements:
//
//ls(path)
//
//mkdir(path)
//
//addContentToFile(path, content)
//
//readContentFromFile(path)

import org.example.fileSystem.trie.model.TrieNode;
import org.example.fileSystem.trie.service.FileSystem;

import java.util.HashMap;
import java.util.List;

public class FileSystemTrieApp {

    public static void main(String[] args) {
        TrieNode root = new TrieNode(new HashMap<>(), false,null);
        FileSystem fs = new FileSystem(root);
        // Create directories
        fs.mkDir("/a/b/c");

        // Add file
        fs.addContentToFile("/a/b/c/file.txt", "Hello ");
        fs.addContentToFile("/a/b/c/file.txt", "World!");

        // List root
        System.out.println("ls(/): " + fs.ls("/"));

        // List directory
        System.out.println("ls(/a/b): " + fs.ls("/a/b"));

        // List specific file
        System.out.println("ls(/a/b/c/file.txt): " + fs.ls("/a/b/c/file.txt"));

        // Read file
        System.out.println("readContent: " + fs.readContentFromFile("/a/b/c/file.txt"));
        fs.addContentToFile("/a/file3.txt", "Test");
        System.out.println("lsR(/a):");
        List<String> files = fs.lsR("/a");
        for (String file : files) {
            System.out.println(file);
        }
    }
}
