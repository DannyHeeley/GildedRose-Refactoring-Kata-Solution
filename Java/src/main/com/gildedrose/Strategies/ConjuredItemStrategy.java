package com.gildedrose.Strategies;

import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

public class ConjuredItemStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    ConjuredItemStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @Override
    public void update(Item item) {
        if (item.getItemType().equals(ItemType.CONJURED)) {
            itemManager.reduceQualityBy(2, item);
        }
    }
}
