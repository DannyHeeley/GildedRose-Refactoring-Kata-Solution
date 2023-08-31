package com.gildedrose.Core;

import com.gildedrose.Core.Concert;
import com.gildedrose.Core.GetDateTime;
import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemInterface;
import com.gildedrose.ItemStrategies.ItemStrategies;

public class GildedRose {
    private ItemInterface[] items;
    private Concert concert;
    private final ItemStrategies itemStrategies;

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

    public ItemInterface[] getItems() {
        return items;
    }
    public void setItems(ItemInterface[] items) {
        this.items = items;
    }
    public Concert getConcert() {
        return concert;
    }
    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
