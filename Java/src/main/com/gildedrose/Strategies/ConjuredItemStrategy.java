package com.gildedrose.Strategies;

import com.gildedrose.GildedRose;
import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

public class ConjuredItemStrategy implements ItemStrategy {
    @Override
    public void updateItem(Item item) {
        if (item.getItemType().equals(ItemType.CONJURED)) {
            GildedRose.reduceQualityBy(2, item);
        }
    }
}
