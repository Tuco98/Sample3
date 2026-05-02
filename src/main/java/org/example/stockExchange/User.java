package org.example.stockExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userId;
    private Double balance;
    Map<String, Integer> holdings = new HashMap<>();
    List<Trade> tradeHistory = new ArrayList<>();

    public User(String userId, Double balance, Map<String, Integer> holdings, List<Trade> tradeHistory) {
        this.userId = userId;
        this.balance = balance;
        this.holdings = holdings;
        this.tradeHistory = tradeHistory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public List<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(List<Trade> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public void addBalance(double b){
        this.balance+=b;
    }

    public void addStocks(String stockId, int qty){
        holdings.put(stockId, holdings.getOrDefault(stockId,0)+qty);
    }

    public boolean removeStocks(String stockId, int qty){
        int curr = holdings.getOrDefault(stockId,0);
        if(curr<qty) return false;
        holdings.put(stockId,curr-qty);
        return true;
    }
}
