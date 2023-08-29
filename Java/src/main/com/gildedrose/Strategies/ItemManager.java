package com.gildedrose.Strategies;

import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

public class ItemManager {
    public void reduceQualityBy(int amount, Item item) {
        if (!item.getName().contains(String.valueOf(ItemType.SULFURAS))) {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() - amount);
            } else {
                item.setQuality(49);
            }
        }
    }

    public void reduceSellInByOne(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    public void increaseQualityBasedOnRemainingDaysToSell(Item item) {
        if (item.getSellIn() < 11 && item.getSellIn() > 5) {
            increaseQualityBy(2, item);
        }
        if (item.getSellIn() < 6) {
            increaseQualityBy(3, item);
        }
    }

    public void increaseQualityBy(int amount, Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + amount);
        }
    }
}
