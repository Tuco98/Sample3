package org.example.splitwise;

public class ExactStrategy implements SplitStrategy{
    @Override
    public void validate(Expense expense) {
        double sum = 0;
        for (Split s:expense.splits){
            sum+=s.amount;
        }
        if (Math.abs(sum- expense.amount)>0.01){
            throw new RuntimeException("Not validated");
        }
    }
}
