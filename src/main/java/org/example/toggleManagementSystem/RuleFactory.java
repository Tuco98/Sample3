package org.example.toggleManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class RuleFactory {
    private static final Map<String, RuleEvaluator> registry = new HashMap<>();

    static {
        registry.put("USER",new UserRule());
        registry.put("PERCENTAGE", new PercentageRule());
        registry.put("ATTRIBUTE", new AttributeRule());
    }

    public static RuleEvaluator getEvaluator(String type){
        return registry.get(type);
    }
}
