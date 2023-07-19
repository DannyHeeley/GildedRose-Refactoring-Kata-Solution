package com.gildedrose;

import com.gildedrose.Concerts.Concert;
import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemStrategies;
import com.gildedrose.Items.UniqueItems;

public class GildedRose implements UniqueItems {

    Item[] items;
    Concert concert;
    ItemStrategies itemStrategies;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemStrategies = new ItemStrategies();
    }

    public GildedRose(Item[] items, Concert concert) {
        this.items = items;
        this.concert = concert;
        this.itemStrategies = new ItemStrategies();
    }

    public void updateQuality() {
        for (Item item : items) {
            if (concert != null) {
                handleItemsIfConcertHasEnded(concert, item);
            } else {
                itemStrategies.useStrategyFor(item);
                reduceSellInByOne(item);
            }
        }
    }

    private void handleItemsIfConcertHasEnded(Concert concert, Item item) {
        if (concert.isConcertOver(new GetDateTime())) {
            reduceQualityBy(item.quality, item);
        }
    }

    public static void reduceQualityBy(int amount, Item item) {
        if (!item.name.contains(SULFURAS)) {
            if (item.quality < 50) {
                item.quality = item.quality - amount;
            } else {
                item.quality = 49;
            }
        }
    }

    public static void increaseQualityBy(int amount, Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + amount;
        }
    }

    void reduceSellInByOne(Item item) {
            item.sellIn = item.sellIn - 1;
    }
}
