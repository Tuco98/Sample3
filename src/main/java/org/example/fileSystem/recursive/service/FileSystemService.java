package org.example.fileSystem.recursive.service;

import org.example.fileSystem.recursive.model.Directory;
import org.example.fileSystem.recursive.model.FileNode;

import java.util.ArrayList;
import java.util.List;

public class FileSystemService {
    public List<String> lsR(Directory root){
        List<String> result = new ArrayList<>();
        dfs(root,"",result);
        return result;
    }

    private void dfs(FileNode node, String path, List<String> result) {
        String currentPath = path.isEmpty()?node.getName():path+"/"+node.getName();

        if(node.isFile()){
            result.add(node.getName());
            return;
        }

        Directory dir = (Directory) node;

        for(FileNode child: dir.getChildren()){
            dfs(child,currentPath,result);
        }
    }
}
