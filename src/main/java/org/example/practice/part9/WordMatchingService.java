package org.example.practice.part9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordMatchingService {

    public static Map<MatchType,MatchStrategy> strategyMap = new HashMap<>();
    static {
        strategyMap.put(MatchType.EXACT, new ExactMatchStrategy());
        strategyMap.put(MatchType.PREFIX, new PrefixMatchStrategy());
        strategyMap.put(MatchType.SUFFIX, new SuffixMatchStrategy());
    }

    public Map<MatchType, List<String>> search (String word){
        Map<MatchType, List<String>> response = new HashMap<>();
        for (MatchType m: MatchType.values()){
            MatchStrategy strategy = strategyMap.get(m);

            List<String> match = strategy.match(word);

            response.put(m,match);

        }

        return response;
    }
}
