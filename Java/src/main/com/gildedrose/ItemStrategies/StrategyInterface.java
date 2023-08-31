package com.gildedrose.ItemStrategies;

import com.gildedrose.Items.ItemInterface;

public interface StrategyInterface {
    void update(ItemInterface item);

    default void increaseQualityBasedOnRemainingDaysToSell(ItemInterface item) {
        if (item.getSellIn() < 11 && item.getSellIn() > 5) {
            item.increaseQualityBy(2);
        }
        if (item.getSellIn() < 6) {
            item.increaseQualityBy(3);
        }
    }
}
