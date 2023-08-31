package com.gildedrose.ItemStrategies;

import com.gildedrose.Items.Conjured;
import com.gildedrose.Items.ItemInterface;

public class ConjuredItemStrategy implements StrategyInterface {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof Conjured) {
            item.reduceQualityBy(2);
        }
    }
}
