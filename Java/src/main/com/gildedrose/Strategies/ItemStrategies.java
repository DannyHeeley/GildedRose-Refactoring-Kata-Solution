package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

import java.util.HashMap;
import java.util.Map;

public class ItemStrategies {
    private final Map<ItemType, ItemUpdateStrategy> strategies;
    private final ItemManager itemManager;

    public ItemStrategies(ItemManager itemManager) {
        this.itemManager = itemManager;
        strategies = new HashMap<>();
        strategies.put(ItemType.AGED, new AgedItemStrategy(itemManager));
        strategies.put(ItemType.SULFURAS, new SulfurasItemStrategy(itemManager));
        strategies.put(ItemType.BACKSTAGE_PASSES, new BackstagePassStrategy(itemManager));
        strategies.put(ItemType.CONJURED, new AgedItemStrategy(itemManager));
    }

    public void useStrategyFor(ItemBase item) {
        ItemUpdateStrategy strategy = strategies.get(item.getItemType());
        if (strategy != null) {
            strategy.update(item);
        } else {
            itemManager.handleItemDefault(item);
            itemManager.handleExpiredItem(item);
        }
    }

    public Map<ItemType, ItemUpdateStrategy> getStrategies() {
        return strategies;
    }

}
