package org.example.stockExchange;

import java.util.*;

public class OrderBook {
    TreeMap<Double, Queue<Order>> buyOrders = new TreeMap<>(Collections.reverseOrder());
    TreeMap<Double, Queue<Order>> sellOrders = new TreeMap<>();

    public List<Trade> matchOrders(Map<String, User> users){
        List<Trade> trades = new ArrayList<>();
        
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()){
            Double bestBuyPrice = buyOrders.firstKey();
            Double bestSellPrice = sellOrders.firstKey();

            if(bestBuyPrice < bestSellPrice) break;

            Queue<Order> buyQueue = buyOrders.get(bestBuyPrice);
            Queue<Order> sellQueue = sellOrders.get(bestSellPrice);

            Order buyOrder = buyQueue.peek();
            Order sellOrder = sellQueue.peek();

            int qty = Math.min(buyOrder.getQty(),sellOrder.getQty());
            double tradePrice = sellOrder.getPrice();

            Trade t = new Trade(UUID.randomUUID().toString(),buyOrder.getOrderId(),sellOrder.getOrderId(),buyOrder.getStockId(),tradePrice,qty,System.currentTimeMillis());

            executeTrade(users, buyOrder, sellOrder, qty, tradePrice);

            trades.add(t);
            int qty1 = buyOrder.getQty();
            buyOrder.setQty(qty1-qty);

            int qty2 = sellOrder.getQty();
            buyOrder.setQty(qty2-qty);

            if (buyQueue.isEmpty()) buyOrders.remove(bestBuyPrice);
            if (sellQueue.isEmpty()) sellOrders.remove(bestSellPrice);


        }

        return trades;
    }

    public void executeTrade(Map<String, User> users, Order buyOrder, Order sellOrder, int qty, double tradePrice) {
        User buyer = users.get(buyOrder.getUserId());
        User seller = users.get(sellOrder.getUserId());

        double tot = qty*tradePrice;
        double buyerBalance = buyer.getBalance();
        buyer.setBalance(buyerBalance-tot);

        double sellerBalance = seller.getBalance();
        seller.setBalance(sellerBalance+tot);

        buyer.addStocks(buyOrder.getStockId(),qty);
        seller.removeStocks(sellOrder.getStockId(), qty);
    }

    public void addOrders(Order order){
        TreeMap<Double, Queue<Order>> doubleQueueTreeMap = order.getOrderType().equals(OrderType.BUY) ? buyOrders : sellOrders;
        doubleQueueTreeMap.putIfAbsent(order.getPrice(), new LinkedList<>());
        doubleQueueTreeMap.get(order.getPrice()).offer(order);
    }
}
