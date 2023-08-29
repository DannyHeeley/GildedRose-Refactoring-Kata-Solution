package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class BackstagePassStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    BackstagePassStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @Override
    public void update(ItemBase item) {
        if (item.getItemType().equals(ItemType.BACKSTAGE_PASSES)) {
            itemManager.increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }
}
