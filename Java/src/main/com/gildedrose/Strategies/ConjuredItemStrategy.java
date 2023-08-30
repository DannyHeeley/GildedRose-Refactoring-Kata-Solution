package com.gildedrose.Strategies;

import com.gildedrose.Items.Conjured;
import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemInterface;

public class ConjuredItemStrategy implements ItemUpdateStrategy {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof Conjured) {
            item.reduceQualityBy(2);
        }
    }
}
