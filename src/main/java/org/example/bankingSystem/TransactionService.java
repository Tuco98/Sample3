package org.example.bankingSystem;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.bankingSystem.AccountService.accountMap;

public class TransactionService {
    Map<String, Transaction> transactionMap = new HashMap<>();

    //    - Transfer money between accounts
    Optional transfer(String fromId, String toId, int timestamp, double amount) {
        Account fromAccount = accountMap.get(fromId);
        Account toAccount = accountMap.get(toId);

        if(fromAccount == null || toAccount == null){
            return Optional.empty();
        }

        Transaction transaction = new Transaction(fromId, toId, timestamp, amount);
        double fromBalance = fromAccount.getBalance();
        double toBalance = toAccount.getBalance();
        fromAccount.setBalance(fromBalance-amount);
        toAccount.setBalance(toBalance+amount);
        transaction.setStatus("COMPLETE");
        transactionMap.put(transaction.getTransactionId(), transaction);

        return Optional.of(transaction);
    }

//    - Returns top N accounts based on outgoing transactions, sorted by amount (descending) and then by account ID (ascending)
    List<Account> topSpenders(int timestamp, int n) {
        PriorityQueue<Transaction> pq = new PriorityQueue<>((a,b)->{
            if(b.getAmount() == a.getAmount()){
                return a.getFromId().compareTo(b.getFromId());
            }else{
                return (int) (b.getAmount() - a.getAmount());
            }
        });

        for(Map.Entry<String, Transaction> e:transactionMap.entrySet()){
            pq.offer(e.getValue());
        }

        int i=0;
        List<Account> accounts = new ArrayList<>();
        while(i<n && !pq.isEmpty()){
            Transaction poll = pq.poll();
            accounts.add(accountMap.get(poll.getFromId()));
        }
        return accounts;
    }

//    - Schedule payments with cashback
    void schedulePayment(String accountId, String targetAccId, int timestamp, int amount, double cashbackPercentage) {
        double cashBack = (amount*cashbackPercentage)/100;
        Transaction transaction = new Transaction(accountId,targetAccId,timestamp,amount-cashBack);
        transaction.setStatus("SCHEDULED");

        transactionMap.put(transaction.getTransactionId(),transaction);

    }

//     - Check if payment is scheduled, processed, or failed
    String getPaymentStatus(String accountId, int timestamp, String paymentId){
        Transaction transaction = transactionMap.get(paymentId);
        return transaction.getStatus();
    }

//    - Execute scheduled payments and apply cashback
    void processScheduledPayments(int currentTimestamp) {
        List<Transaction> scheduled = transactionMap.entrySet().stream().filter(t -> t.getValue().getStatus().equals("SCHEDULED"))
                .map(e -> e.getValue()).collect(Collectors.toList());

        for(Transaction t: scheduled){
            transfer(t.getFromId(),t.getToId(),currentTimestamp,t.getAmount());//bad design
        }
    }

//    - Merge two accounts, combining balances and updating all transaction histories
    void mergeAccounts(String accountId1, String accountId2) {
        Account account1 = accountMap.get(accountId1);
        Account account2 = accountMap.get(accountId2);

        if(account1 == null || account2 == null) return;

        double balance = account1.getBalance()+account2.getBalance();
        account1.setBalance(balance);
        account2.setStatus("Merged");

        for(Map.Entry<String, Transaction> e: transactionMap.entrySet()){
            Transaction t = e.getValue();

            if(t.getFromId().equals(accountId2)){
                t.setFromId(accountId1);
            }

            if(t.getToId().equals(accountId2)){
                t.setToId(accountId1);
            }
        }
    }




}
