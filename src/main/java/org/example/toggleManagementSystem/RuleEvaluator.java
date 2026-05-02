package org.example.toggleManagementSystem;

import java.util.Map;

public interface RuleEvaluator {
    boolean evaluate(UserContext userContext, Map<String, Object> configs);
}
