package com.company;

import java.util.HashMap;
import java.util.Map;

public class OrderBook {
    private Map<Integer, Integer> bidMap = new HashMap<>();
    private Map<Integer, Integer> askMap = new HashMap<>();
    private Map<Integer, Integer> spreadMap = new HashMap<>();

    public void addToBidMap(Integer key, Integer value) {
        bidMap.put(key, value);
    }

    public void addToAskMap(Integer key, Integer value) {
        askMap.put(key, value);
    }

    public void addToSpreadMap(Integer key, Integer value) {
        spreadMap.put(key, value);
    }

    public String getBestBid() {
        Integer biggestKey = bidMap.keySet()
            .stream()
            .max(Integer::compare)
            .orElseThrow(() -> new RuntimeException("BidMap is empty"));
        return biggestKey + "," + bidMap.get(biggestKey);
    }

    public String getBestAsk() {
        Integer lowestKey = askMap.keySet()
            .stream()
            .min(Integer::compare)
            .orElseThrow(() -> new RuntimeException("AskMap is empty"));
        return lowestKey + "," + askMap.get(lowestKey);
    }

    public Integer sizeByPrice(Integer key) {
        if (bidMap.get(key) != null) {
            return bidMap.get(key);
        }
        if (askMap.get(key) != null) {
            return askMap.get(key);
        }
        return spreadMap.get(key);
    }

    public void removeFromBid(int size) {
        Integer key = Integer.parseInt(getBestBid().split(",")[0]);
        Integer value = bidMap.get(key);
        if (value != null) {
            value -= size;
            bidMap.put(key, value);
        }
    }

    public void removeFromAsk(int size) {
        Integer key = Integer.parseInt(getBestAsk().split(",")[0]);
        Integer value = askMap.get(key);
        if (value != null) {
            value -= size;
            askMap.put(key, value);
        }
    }
}
