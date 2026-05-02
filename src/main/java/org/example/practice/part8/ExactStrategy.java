package org.example.practice.part8;

import java.util.List;

public class ExactStrategy implements SplitStrategy{
    @Override
    public void validate(Expense e) {
        List<Split> splits = e.splits;
        double sum= 0;
        for (Split s: splits){
            sum+=s.amount;
        }

        if(sum-e.amt > 0.01){
            throw new RuntimeException("Not matching");
        }
    }
}
