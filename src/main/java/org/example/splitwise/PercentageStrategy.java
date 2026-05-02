package org.example.splitwise;

public class PercentageStrategy implements SplitStrategy{
    @Override
    public void validate(Expense expense) {
        double sum = 0;
        for (Split s:expense.splits){
            PercentageSplit p = (PercentageSplit) s;
            s.amount = p.percentage* expense.amount / 100.0;
            sum+=s.amount;
        }

        if(Math.abs(sum-100.0)>0.01){
            throw new RuntimeException("Not matching");
        }
    }
}
