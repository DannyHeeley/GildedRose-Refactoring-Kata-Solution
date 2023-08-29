package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class ConjuredItemStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    ConjuredItemStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @Override
    public void update(ItemBase item) {
        if (item.getItemType().equals(ItemType.CONJURED)) {
            itemManager.reduceQualityBy(2, item);
        }
    }
}
