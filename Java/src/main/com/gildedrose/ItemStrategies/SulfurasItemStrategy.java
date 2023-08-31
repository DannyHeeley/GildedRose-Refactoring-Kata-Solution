package com.gildedrose.ItemStrategies;

import com.gildedrose.Items.ItemInterface;
import com.gildedrose.Items.Sulfuras;

public class SulfurasItemStrategy implements StrategyInterface {
    @Override
    public void update(ItemInterface item) {
        if (item instanceof Sulfuras) {
            item.setQuality(80);
            item.setSellIn(Integer.MAX_VALUE);
        }
    }
}
