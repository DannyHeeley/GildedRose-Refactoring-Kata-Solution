package com.gildedrose.ItemStrategies;

import com.gildedrose.Items.Aged;
import com.gildedrose.Items.ItemInterface;

public class AgedItemStrategy implements StrategyInterface {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof Aged) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }
}
