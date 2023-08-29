package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class AgedItemStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    AgedItemStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @Override
    public void update(ItemBase item) {
        if (item.getItemType().equals(ItemType.AGED)) {
            itemManager.increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }
}
