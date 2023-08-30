package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class BackstagePassStrategy implements ItemUpdateStrategy {
    @Override
    public void update(ItemBase item) {
        if (item.getItemType().equals(ItemType.BACKSTAGE_PASSES)) {
            item.increaseQualityBasedOnRemainingDaysToSell();
        }
    }
}
