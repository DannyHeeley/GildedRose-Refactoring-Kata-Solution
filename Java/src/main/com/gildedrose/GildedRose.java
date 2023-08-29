package com.gildedrose;

import com.gildedrose.Items.Item;
import com.gildedrose.Strategies.ItemStrategies;
import com.gildedrose.Items.ItemType;

public class GildedRose {

    Item[] items;
    Concert concert;
    ItemStrategies itemStrategies;

    public GildedRose(Item[] items, ItemStrategies itemStrategies) {
        this.items = items;
        this.itemStrategies = itemStrategies;
    }

    public GildedRose(Item[] items, ItemStrategies itemStrategies, Concert concert) {
        this.items = items;
        this.itemStrategies = itemStrategies;
        this.concert = concert;
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
            reduceQualityBy(item.getQuality(), item);
        }
    }

    public static void reduceQualityBy(int amount, Item item) {
        if (!item.getName().contains(String.valueOf(ItemType.SULFURAS))) {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() - amount);
            } else {
                item.setQuality(49);
            }
        }
    }

    void reduceSellInByOne(Item item) {
            item.setSellIn(item.getSellIn() - 1);
    }
}
