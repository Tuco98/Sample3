package org.example.practice.part9;

import java.util.ArrayList;
import java.util.List;

public class SuffixMatchStrategy implements MatchStrategy{
    @Override
    public List<String> match(String word) {
        List<String> res = new ArrayList<>();
        for(String w: Dictionary.dictionary){
            if(w.endsWith(word)){
                res.add(w);
            }
        }

        return res;
    }
}
