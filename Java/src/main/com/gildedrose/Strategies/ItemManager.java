package com.gildedrose.Strategies;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemType;

public class ItemManager {
    public void reduceQualityBy(int amount, ItemBase item) {
        if (!item.getName().contains(String.valueOf(ItemType.SULFURAS))) {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() - amount);
            } else {
                item.setQuality(49);
            }
        }
    }

    public void reduceSellInByOne(ItemBase item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    public void increaseQualityBasedOnRemainingDaysToSell(ItemBase item) {
        if (item.getSellIn() < 11 && item.getSellIn() > 5) {
            increaseQualityBy(2, item);
        }
        if (item.getSellIn() < 6) {
            increaseQualityBy(3, item);
        }
    }

    public void increaseQualityBy(int amount, ItemBase item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + amount);
        }
    }

    public void handleItemDefault(ItemBase item) {
        if (item.getSellIn() > -1)
        {
            reduceQualityBy(1, item);
        }
    }

    public void handleExpiredItem(ItemBase item) {
        if (!itemHasStrategy(item.getItemType())
                && item.getSellIn() < 0)
        {
            reduceQualityBy(2, item);
        }
    }

    public boolean itemHasStrategy(ItemType itemType) {
        ItemStrategies itemStrategies = new ItemStrategies(this);
        return itemStrategies.getStrategies().containsKey(itemType);
    }
}
