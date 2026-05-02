package org.example.practice.part8;

import java.util.List;

public class ExpenseService {
    BalanceService balanceService = new BalanceService();
    SplitStrategyFactory factory = new SplitStrategyFactory();
    public  void addExpense(Expense e){

        SplitStrategy strategyImpl = factory.getStrategyImpl(e.splitType);

        strategyImpl.validate(e);

        for(Split s: e.splits){
            if(s.userId.equals(e.paidBy)) continue;
            balanceService.updateBalance(s.userId,e.paidBy,s.amount);
        }


    }

}
