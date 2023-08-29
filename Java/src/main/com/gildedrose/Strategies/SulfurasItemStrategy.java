package com.gildedrose.Strategies;

import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

public class SulfurasItemStrategy implements ItemUpdateStrategy {
    ItemManager itemManager;
    SulfurasItemStrategy(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    @Override
    public void update(Item item) {
        if (item.getItemType().equals(ItemType.SULFURAS)) {
            item.setQuality(80);
            item.setSellIn(Integer.MAX_VALUE);
        }
    }
}
