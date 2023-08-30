package com.gildedrose.Strategies;

import com.gildedrose.Items.*;

import java.util.HashMap;
import java.util.Map;

public class ItemStrategies {
    private final Map<String, ItemUpdateStrategy> strategies;
    public ItemStrategies() {
        strategies = new HashMap<>();
        strategies.put(Aged.class.getSimpleName(), new AgedItemStrategy());
        strategies.put(Sulfuras.class.getSimpleName(), new SulfurasItemStrategy());
        strategies.put(BackstagePass.class.getSimpleName(), new BackstagePassStrategy());
        strategies.put(BackstagePass.class.getSimpleName(), new ConjuredItemStrategy());
    }
    public void useStrategyFor(ItemInterface item) {
        System.out.println(item.getClass().getSimpleName());
        ItemUpdateStrategy strategy = strategies.get(item.getClass().getSimpleName());
        if (strategy != null) {
            strategy.update(item);
        } else {
            handleItemDefault(item);
            handleExpiredItem(item);
        }
    }
    public void handleItemDefault(ItemInterface item) {
        if (item.getSellIn() > -1) {
            item.reduceQualityBy(1);
        }
    }
    public void handleExpiredItem(ItemInterface item) {
        if (!itemHasStrategy(item) && item.getSellIn() < 0) {
            item.reduceQualityBy(2);
        }
    }
    public boolean itemHasStrategy(ItemInterface item) {
        return strategies.containsKey(item.getClass().getSimpleName());
    }
}
