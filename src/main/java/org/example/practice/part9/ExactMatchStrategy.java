package org.example.practice.part9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExactMatchStrategy implements MatchStrategy{
    @Override
    public List<String> match(String word) {
        if(Dictionary.checkWord(word)){
            return Arrays.asList(word);
        }
        return Collections.emptyList();
    }
}
