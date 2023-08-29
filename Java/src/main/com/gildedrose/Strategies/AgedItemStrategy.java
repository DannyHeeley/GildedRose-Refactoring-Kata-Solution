package com.gildedrose.Strategies;

import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

public class AgedItemStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    AgedItemStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @Override
    public void update(Item item) {
        if (item.getItemType().equals(ItemType.AGED)) {
            itemManager.increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }
}
