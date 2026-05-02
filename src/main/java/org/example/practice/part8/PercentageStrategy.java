package org.example.practice.part8;

public class PercentageStrategy implements SplitStrategy{
    @Override
    public void validate(Expense e) {
        double total = 0;
        for (Split s: e.splits){
            PercentageSplit p = (PercentageSplit) s;
            s.amount = p.percentage* e.amt / 100;
            total+=s.amount;
        }

        if(total - 100.0 > 0.01){
            throw new RuntimeException("Not Validated");
        }
    }
}
