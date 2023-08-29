package com.gildedrose;

import com.gildedrose.Items.Item;
import com.gildedrose.Strategies.ItemManager;
import com.gildedrose.Strategies.ItemStrategies;
import com.gildedrose.Items.ItemType;

public class GildedRose {

    Item[] items;
    Concert concert;
    ItemStrategies itemStrategies;
    ItemManager itemManager;

    public GildedRose(Item[] items, ItemStrategies itemStrategies, ItemManager itemManager) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.itemManager = itemManager;
    }

    public GildedRose(Item[] items, ItemStrategies itemStrategies, ItemManager itemManager, Concert concert) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.itemManager = itemManager;
        this.concert = concert;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (concert != null) {
                handleItemsIfConcertHasEnded(concert, item);
            } else {
                itemStrategies.useStrategyFor(item);
                itemManager.reduceSellInByOne(item);
            }
        }
    }

    private void handleItemsIfConcertHasEnded(Concert concert, Item item) {
        if (concert.isConcertOver(new GetDateTime())) {
            itemManager.reduceQualityBy(item.getQuality(), item);
        }
    }

}
