package org.example.fileSystem.recursive;

//Implementing filesystem in recursive manner

import org.example.fileSystem.recursive.model.Directory;
import org.example.fileSystem.recursive.model.File;
import org.example.fileSystem.recursive.service.FileSystemService;

import java.util.List;

public class FileSystemRecursiveApp {
    public static void main(String[] args) {

        Directory root = new Directory("/");
        Directory dir1 = new Directory("dir1");
        Directory dir2 = new Directory("dir2");
        File file1 = new File("file1");
        File file2 = new File("file2");

        root.add(dir1);
        dir1.add(dir2);
        dir1.add(file1);
        dir2.add(file2);

        FileSystemService fs = new FileSystemService();
        List<String> res = fs.lsR(dir1);
        System.out.println(res);
    }
}
