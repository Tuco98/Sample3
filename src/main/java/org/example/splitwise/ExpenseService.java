package org.example.splitwise;

import java.util.Objects;

public class ExpenseService {
    BalanceService balanceService;

    public ExpenseService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void addExpense(Expense e){
        SplitStrategy strategy = SplitFactory.getStrategy(e.splitType);
        strategy.validate(e);
        for (Split s: e.splits){
            if (Objects.equals(s.userId, e.paidBy)) continue;
            balanceService.update(s.userId,e.paidBy, s.amount);
        }
    }
}
