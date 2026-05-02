package org.example.fileSystem.trie.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Typical in-memory FS requirements:
//
//ls(path)
//
//mkdir(path)
//
//addContentToFile(path, content)
//
//readContentFromFile(path)

class FileNode{
    Map<String, FileNode> children;
    boolean isFile;
    String content;


}


public class FileSystemService {
    FileNode root = new FileNode();
    //"/"
    public List<String> ls(String path){
//        String[] parts = path.split("/");
        List<String> res = new ArrayList<>();

        FileNode node = traverse(path, false);

        if(node.isFile){
            res.add(path);
            return res;
        }

        res.addAll(node.children.keySet());
        Collections.sort(res);
        return res;

    }

    public void mkDir(String path){
        FileNode node = traverse(path,true);
    }

    public void addContentToFile(String path, String content){
        FileNode node = traverse(path,true);
        if(node.isFile) content = node.content+content;
        node.isFile = true;
        node.content = content;
    }

    public String readContentsFromFile(String path){
        FileNode node = traverse(path, false);
        assert node != null;
        return node.content;
    }

    private FileNode traverse(String path, boolean create){
        String[] paths = path.split("/");
        FileNode curr = root;

        for(String part: paths){
            if(part.isEmpty()) continue;

            if(!curr.children.containsKey(part)){
                if(create){
                    FileNode temp = new FileNode();
                    curr.children.put(part,temp);

                }else{
                    return null;
                }
            }

            curr = curr.children.get(part);
        }

        return curr;
    }






}
