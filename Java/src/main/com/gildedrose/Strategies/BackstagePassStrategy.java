package com.gildedrose.Strategies;

import com.gildedrose.Items.BackstagePass;
import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemInterface;

public class BackstagePassStrategy implements ItemUpdateStrategy {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof BackstagePass) {
            item.increaseQualityBasedOnRemainingDaysToSell();
        }
    }
}
