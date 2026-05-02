package org.example.practice.part4;

import java.util.HashMap;
import java.util.Map;

public class DBStrategyFactory {
    static Map<String, DBStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put("Oracle", new OracleDBStrategy());
        strategyMap.put("MySQL", new MySQLDBStrategy());
    }

    DBStrategy getDBStrategy(String type){
        return strategyMap.get(type);
    }
}
