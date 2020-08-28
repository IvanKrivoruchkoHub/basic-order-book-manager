package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OrderBookManager {
    private OrderBook orderBook = new OrderBook();

    private Map<String, Consumer<String>> functionMap = new HashMap<>();

    public OrderBookManager() {
        functionMap.put("qbestbid", s -> System.out.println(orderBook.getBestBid()));
        functionMap.put("qbestask", s -> System.out.println(orderBook.getBestAsk()));
        functionMap.put("ubid", s -> {
            String[] partsOfCommand = s.split(",");
            orderBook.addToBidMap(Integer.parseInt(partsOfCommand[1]),
                Integer.parseInt(partsOfCommand[2]));
        });
        functionMap.put("uask", s -> {
            String[] partsOfCommand = s.split(",");
            orderBook.addToAskMap(Integer.parseInt(partsOfCommand[1]),
                Integer.parseInt(partsOfCommand[2]));
        });
        functionMap.put("qsize", s -> {
            s = s.replaceAll("q,size,", "");
            System.out.println(orderBook.sizeByPrice(Integer.parseInt(s)));
        });
        functionMap.put("obuy", s -> {
            s = s.replaceAll("o,buy,", "");
            orderBook.removeFromAsk(Integer.parseInt(s));
        });
        functionMap.put("osell", s -> {
            s = s.replaceAll("o,sell,", "");
            orderBook.removeFromBid(Integer.parseInt(s));
        });
    }

    public void executeOperation(String s) {
        s = s.toLowerCase();
        functionMap.get(s.replaceAll("[^a-z]", "")).accept(s);
    }
}
