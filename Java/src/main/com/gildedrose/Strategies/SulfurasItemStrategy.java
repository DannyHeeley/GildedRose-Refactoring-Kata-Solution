package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class SulfurasItemStrategy implements ItemUpdateStrategy {
    @Override
    public void update(ItemBase item) {
        if (item.getItemType().equals(ItemType.SULFURAS)) {
            item.setQuality(80);
            item.setSellIn(Integer.MAX_VALUE);
        }
    }
}
