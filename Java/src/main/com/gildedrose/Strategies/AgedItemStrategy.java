package com.gildedrose.Strategies;

import com.gildedrose.Items.Aged;
import com.gildedrose.Items.ItemInterface;

public class AgedItemStrategy implements ItemUpdateStrategy {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof Aged) {
            item.increaseQualityBasedOnRemainingDaysToSell();
        }
    }
}
