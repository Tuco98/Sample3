package org.example.toggleManagementSystem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FeatureFlagService {

    static Map<String, FeatureFlag> flags = new ConcurrentHashMap<>();

    public void createFlag(String name){
        String id = UUID.randomUUID().toString();
        flags.put(id, new FeatureFlag(id,name));
    }

    public void addRule(String flagId, Rule rule){

        FeatureFlag flag = flags.get(flagId);
        if(flag != null){
            flag.rules.add(rule);
            flag.rules.sort(Comparator.comparingInt(r->r.priority));
        }
    }

    public void disableFlag(String flagId){
        FeatureFlag featureFlag = flags.get(flagId);
        if (featureFlag != null) featureFlag.status = FlagStatus.INACTIVE;
    }

    public Map<String, Boolean> evaluate(UserContext context){
        Map<String, Boolean> res = new HashMap<>();

        for (FeatureFlag flags: flags.values()){
            if(flags.status == FlagStatus.INACTIVE){
                res.put(flags.id, false);
                continue;
            }

            boolean isEnabled = false;

            for (Rule rule: flags.rules){
                RuleEvaluator ruleEvaluator = RuleFactory.getEvaluator(rule.type);
                if(ruleEvaluator!=null && ruleEvaluator.evaluate(context, rule.configs)){
                    isEnabled = true;
                    break;
                }
            }
            res.put(flags.id, isEnabled);
        }

        return res;
    }
}
