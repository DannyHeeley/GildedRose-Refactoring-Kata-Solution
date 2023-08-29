package com.gildedrose.Strategies;

import com.gildedrose.GildedRose;
import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

import java.util.HashMap;
import java.util.Map;


public class ItemStrategies {

    Map<ItemType, ItemUpdateStrategy> strategies;
    ItemManager itemManager;

    public ItemStrategies(ItemManager itemManager) {
        this.itemManager = itemManager;
        strategies = new HashMap<>();
        strategies.put(ItemType.AGED, new AgedItemStrategy(itemManager));
        strategies.put(ItemType.SULFURAS, new SulfurasItemStrategy(itemManager));
        strategies.put(ItemType.BACKSTAGE_PASSES, new BackstagePassStrategy(itemManager));
        strategies.put(ItemType.CONJURED, new AgedItemStrategy(itemManager));
    }

    public void useStrategyFor(Item item) {
        ItemUpdateStrategy strategy = strategies.get(item.getItemType());
        if (strategy != null) {
            strategy.update(item);
        } else {
            handleItemDefault(item);
            handleExpiredItem(item);
        }
    }

    public boolean itemHasStrategy(ItemType itemType) {
        return strategies.containsKey(itemType);
    }

    private void handleItemDefault(Item item) {
        if (item.getSellIn() > -1)
        {
            itemManager.reduceQualityBy(1, item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (!itemHasStrategy(item.getItemType())
            && item.getSellIn() < 0)
        {
            itemManager.reduceQualityBy(2, item);
        }
    }
}
