package org.example.toggleManagementSystem;

import java.util.Map;

public class UserRule implements RuleEvaluator{
    @Override
    public boolean evaluate(UserContext userContext, Map<String, Object> configs) {
        return false;
    }
}
