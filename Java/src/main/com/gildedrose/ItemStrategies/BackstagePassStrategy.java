package com.gildedrose.ItemStrategies;

import com.gildedrose.Items.BackstagePass;
import com.gildedrose.Items.ItemInterface;

public class BackstagePassStrategy implements StrategyInterface {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof BackstagePass) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }
}
