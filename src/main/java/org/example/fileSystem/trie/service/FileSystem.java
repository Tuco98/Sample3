package org.example.fileSystem.trie.service;

import org.example.fileSystem.trie.model.TrieNode;

import java.util.*;

public class FileSystem {

    public final TrieNode root;

    public FileSystem(TrieNode root) {
        this.root = root;
    }

    public List<String> lsR(String path){
        TrieNode node = traverse(path, false);

        List<String> res = new ArrayList<>();

        if(node == null) return res;

        String normalisedPath = path.equals("/")?"":path;
        dfs(node,normalisedPath,res);
        Collections.sort(res);
        return res;
    }

    private void dfs(TrieNode node, String normalisedPath, List<String> res) {
        if(node.isFile()){
            res.add(normalisedPath);
            return;
        }

        for(Map.Entry<String, TrieNode> e:node.getChildren().entrySet()){
            String key = e.getKey();
            TrieNode tempNode = e.getValue();

            dfs(tempNode, normalisedPath+"/"+key,res);
        }
    }

    public List<String> ls(String path){
        TrieNode node = traverse(path, false);
        List<String> res = new ArrayList<>();
        if(node == null) return null;
        if(node.isFile()){
            String[] parts = path.split("/");
            res.add(parts[parts.length-1]);
            return res;
        }

        res.addAll(node.getChildren().keySet());
        Collections.sort(res);
        return res;

    }

    public void mkDir(String path){
        traverse(path, true);
    }

    public void addContentToFile(String path, String content){
        TrieNode node = traverse(path, true);
        if(node.isFile()) content=node.getContent()+content;
        node.setFile(true);
        node.setContent(content);
    }

    public String readContentFromFile(String path){
        TrieNode node = traverse(path, false);
        return node.getContent();
    }

    public TrieNode traverse(String path, boolean create ){

        String[] paths = path.split("/");
        TrieNode curr = root;

        for(String part: paths){
//            String part = paths[i];
            if(part.isEmpty()) continue;

            if(!curr.getChildren().containsKey(part)){
                if(create){
                    TrieNode node = new TrieNode(new HashMap<>(),false,null);
                    curr.getChildren().put(part,node);
                    curr = curr.getChildren().get(part);
                }else {
                    return null;
                }
            }else {
                curr = curr.getChildren().get(part);
            }
        }

        return curr;

    }
}
