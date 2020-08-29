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
        Integer biggestKeyWithNoneZeroSize = bidMap.entrySet()
            .stream()
            .filter(entry -> entry.getValue() != null)
            .map(Map.Entry::getKey)
            .max(Integer::compare)
            .orElseThrow(() -> new RuntimeException("BidMap is empty"));
        return biggestKeyWithNoneZeroSize + ","
            + bidMap.get(biggestKeyWithNoneZeroSize);
    }

    public String getBestAsk() {
        Integer lowestKeyWithNoneZeroSize = bidMap.entrySet()
            .stream()
            .filter(entry -> entry.getValue() != null)
            .map(Map.Entry::getKey)
            .min(Integer::compare)
            .orElseThrow(() -> new RuntimeException("AskMap is empty"));
        return lowestKeyWithNoneZeroSize + ","
            + askMap.get(lowestKeyWithNoneZeroSize);
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
