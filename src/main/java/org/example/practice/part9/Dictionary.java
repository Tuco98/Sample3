package org.example.practice.part9;

import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    public static Set<String> dictionary = new HashSet<>();

    public static void addWord(String word){
        dictionary.add(word);
    }

    public static boolean checkWord(String word){
        return dictionary.contains(word);
    }
}
