package org.example.splitwise;

public class EqualStrategy implements SplitStrategy{
    @Override
    public void validate(Expense expense) {
        int n = expense.splits.size();
        double amt = expense.amount / n;
        for (Split s:expense.splits){
            s.amount = amt;
        }
    }
}
