package org.example.practice.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class XMLParser {
    public Node parse(String s){
        Node root = null;
        Stack<Node> st = new Stack<>();

        int i=0;
        int n = s.length();


        while (i<n){

            if(s.charAt(i) == '<'){
                int j = s.indexOf('>', i);
                String tag = s.substring(i+1,j);

                if(tag.charAt(0) == '/'){
                    st.pop();
                }else{

                    Node node = new Node(tag);
                    if(!st.isEmpty()){
                        st.peek().children.add(node);
                    }else{
                        root = node;
                    }

                    st.push(node);
                }

                i = j+1;

            }else{
                i++;
            }
        }

        return root;


    }

    public void print(Node node){

        Queue<Node> q = new LinkedList<>();

        q.offer(node);
        int level = 0;
        while (!q.isEmpty()){
            int size = q.size();
            StringBuilder spaces = new StringBuilder();
            for(int p = 0;p<level;p++){
                spaces.append(" ");
            }
            for(int i=0;i<size;i++){
                Node curr = q.poll();
                assert curr != null;
                System.out.println(spaces+curr.tag);
                for(Node n:curr.children){
                    q.offer(n);
                }
            }
            level++;
        }
    }

    public static void main(String[] args) {
        String s = "<a><b></b><c></c></a>";
        XMLParser parser = new XMLParser();
        Node node = parser.parse(s);
        parser.print(node);
    }
}
