package com.gildedrose;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class ItemStrategies implements UniqueItems {

    Map<String, Consumer<Item>> strategies;

    public ItemStrategies() {
        strategies = new HashMap<>();
        strategies.put(AGED, this::handleAgedItem);
        strategies.put(SULFURAS, this::handleSulfurasItem);
        strategies.put(BACKSTAGE_PASSES, this::handleBackstagePassItem);
        strategies.put(CONJURED, this::handleConjuredItem);
    }

    public void useStrategyFor(Item item) {
        Consumer<Item> strategy = getStrategyIfStrategiesContains(item.name);
        if (strategy != null) {
            strategy.accept(item);
        } else {
            handleItemDefault(item);
            handleExpiredItem(item);
        }
    }

    private Consumer<Item> getStrategyIfStrategiesContains(String itemName) {
        Consumer<Item> strategy = null;
        for (String key : strategies.keySet()) {
            if (itemName.contains(key)) {
                strategy = strategies.get(key);
                break;
            }
        }
        return strategy;
    }

    public boolean isItemInItemStrategies(String itemName) {
        return strategies.containsKey(itemName);
    }

    private void handleItemDefault(Item item) {
        if (item.sellIn > -1)
        {
            GildedRose.reduceQualityBy(1, item);
        }
    }

    private void handleAgedItem(Item item) {
        if (item.name.contains(AGED)) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }

    private void handleBackstagePassItem(Item item) {
        if (item.name.contains(BACKSTAGE_PASSES)) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }

    private void handleSulfurasItem(Item item) {
        if (item.name.contains(SULFURAS)) {
            item.quality = 80;
            item.sellIn = Integer.MAX_VALUE;
        }
    }

    private void handleConjuredItem(Item item) {
        if (item.name.contains(CONJURED)) {
            GildedRose.reduceQualityBy(2, item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (!isItemInItemStrategies(item.name)
            && item.sellIn < 0)
        {
            GildedRose.reduceQualityBy(2, item);
        }
    }

    private void increaseQualityBasedOnRemainingDaysToSell(Item item) {
        if (item.sellIn < 11 && item.sellIn > 5) {
            GildedRose.increaseQualityBy(2, item);
        }
        if (item.sellIn < 6) {
            GildedRose.increaseQualityBy(3, item);
        }
    }

}
