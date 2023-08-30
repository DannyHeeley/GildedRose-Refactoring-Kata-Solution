package com.gildedrose;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemInterface;
import com.gildedrose.Strategies.ItemStrategies;

public class GildedRose {
    ItemInterface[] items;
    Concert concert;
    ItemStrategies itemStrategies;

    public GildedRose(ItemInterface[] items, ItemStrategies itemStrategies) {
        this.items = items;
        this.itemStrategies = itemStrategies;
    }
    public GildedRose(ItemInterface[] items, ItemStrategies itemStrategies, Concert concert) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.concert = concert;
    }
    public void updateQuality() {
        for (ItemInterface item : items) {
            if (concert != null) {
                handleItemsIfConcertHasEnded(concert, item);
            } else {
                itemStrategies.useStrategyFor(item);
                item.reduceSellInByOne();
            }
        }
    }
    private void handleItemsIfConcertHasEnded(Concert concert, ItemInterface item) {
        if (concert.isConcertOver(new GetDateTime())) {
            item.reduceQualityBy(item.getQuality());
        }
    }
}
