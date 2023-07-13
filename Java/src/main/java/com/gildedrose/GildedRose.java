package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            updateItemsBasedOnName(i, item);
            reduceSellInOfitemBy(1, items, i);

        }

    }

    private void updateItemsBasedOnName(int i, Item item) {
        //Any item but special cases is reduced by one
        if (!item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")
            && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                reduceQualityBy(1, items, i);

        } else {
            handleVariableQualityBasedOnSellIn(i, item);
        }

    }

    private void handleVariableQualityBasedOnSellIn(int i, Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")
            || item.name.equals("Aged Brie")) {
            if (item.sellIn < 11 && item.sellIn > 5) {
                increaseQualityBy(2, items, i);
            }
            if (item.sellIn < 6) {
                increaseQualityBy(3, items, i);
            }
        }

    }

    private void reduceQualityBy(int amount, Item[] items, int i) {
        if (items[i].quality <= 50) {
            items[i].quality = items[i].quality - amount;
        }
    }

    private void increaseQualityBy(int amount, Item[] items, int i) {
        if (items[i].quality <= 50) {
            items[i].quality = items[i].quality + amount;
        }
    }

    private void reduceSellInOfitemBy(int amount, Item[] items, int i) {
        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].sellIn = items[i].sellIn - amount;
        }
    }
}
