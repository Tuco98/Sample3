package org.example.bankingSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountService {
    static Map<String, Account> accountMap = new HashMap<>();
    //- Create new accounts
    boolean createAccount(String accountId, int timestamp) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setTimestamp(timestamp);
        account.setBalance(0L);
        accountMap.put(accountId, account);
        return true;
    }

//    - Deposit money into accounts
    public Optional deposit(String accountId, int timestamp, int amount) {
        if(!accountMap.containsKey(accountId)){
            return Optional.empty();
        }
        Account account = accountMap.get(accountId);
        double balance = account.getBalance();
        balance+=amount;
        account.setBalance(balance);

        return Optional.of(account);
    }



}
