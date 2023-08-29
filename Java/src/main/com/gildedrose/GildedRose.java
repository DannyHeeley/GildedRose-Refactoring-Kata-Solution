package com.gildedrose;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Strategies.ItemManager;
import com.gildedrose.Strategies.ItemStrategies;

public class GildedRose {

    ItemBase[] items;
    Concert concert;
    ItemStrategies itemStrategies;
    ItemManager itemManager;

    public GildedRose(ItemBase[] items, ItemStrategies itemStrategies, ItemManager itemManager) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.itemManager = itemManager;
    }

    public GildedRose(ItemBase[] items, ItemStrategies itemStrategies, ItemManager itemManager, Concert concert) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.itemManager = itemManager;
        this.concert = concert;
    }

    public void updateQuality() {
        for (ItemBase item : items) {
            if (concert != null) {
                handleItemsIfConcertHasEnded(concert, item);
            } else {
                itemStrategies.useStrategyFor(item);
                itemManager.reduceSellInByOne(item);
            }
        }
    }

    private void handleItemsIfConcertHasEnded(Concert concert, ItemBase item) {
        if (concert.isConcertOver(new GetDateTime())) {
            itemManager.reduceQualityBy(item.getQuality(), item);
        }
    }

}
