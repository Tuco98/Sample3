package org.example.practice.part8;

public class EqualStrategy implements SplitStrategy{
    @Override
    public void validate(Expense e) {
        int n = e.splits.size();
        double amt = e.amt/n;
        for(Split s: e.splits){
            s.amount = amt;
        }
    }
}
