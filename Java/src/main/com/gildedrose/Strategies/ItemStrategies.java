package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

import java.util.HashMap;
import java.util.Map;

public class ItemStrategies {
    private final Map<ItemType, ItemUpdateStrategy> strategies;
    public ItemStrategies() {
        strategies = new HashMap<>();
        strategies.put(ItemType.AGED, new AgedItemStrategy());
        strategies.put(ItemType.SULFURAS, new SulfurasItemStrategy());
        strategies.put(ItemType.BACKSTAGE_PASSES, new BackstagePassStrategy());
        strategies.put(ItemType.CONJURED, new ConjuredItemStrategy());
    }
    public void useStrategyFor(ItemBase item) {
        ItemUpdateStrategy strategy = strategies.get(item.getItemType());
        if (strategy != null) {
            strategy.update(item);
        } else {
            handleItemDefault(item);
            handleExpiredItem(item);
        }
    }
    public void handleItemDefault(ItemBase item) {
        if (item.getSellIn() > -1) {
            item.reduceQualityBy(1);
        }
    }
    public void handleExpiredItem(ItemBase item) {
        if (!itemHasStrategy(item.getItemType()) && item.getSellIn() < 0) {
            item.reduceQualityBy(2);
        }
    }
    public boolean itemHasStrategy(ItemType itemType) {
        return strategies.containsKey(itemType);
    }
}
