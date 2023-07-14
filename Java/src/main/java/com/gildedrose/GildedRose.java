package com.gildedrose;
/** @noinspection SpellCheckingInspection*/
class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            handleItemDefault(i, item);
            handleBackstagePassItem(i, item);
            handleAgedItem(i, item);
            handleConjuredItem(i, item);
            handleSulfurasItem(items, i);
            reduceSellInBy(1, items, i);
        }
    }

    private void handleItemDefault(int i, Item item) {
        if (!item.name.contains("Aged")
            && !item.name.contains("Backstage passes")
            && !item.name.contains("Sulfuras")
            && !item.name.contains("Conjured")) {
            reduceQualityBy(1, items, i);
            handleExpiredItem(i, item);
        }
    }

    private void handleExpiredItem(int i, Item item) {
        if (item.sellIn < 1) {
            reduceQualityBy(1, items, i);
        }
    }

    private void handleBackstagePassItem(int i, Item item) {
        if (item.name.contains("Backstage passes")) {
            handleQualityBySellInValueRanges(i, item);
        }
    }

    private void handleAgedItem(int i, Item item) {
        if (item.name.contains("Aged")) {
            handleQualityBySellInValueRanges(i, item);
        }
    }

    private void handleConjuredItem(int i, Item item) {
        if (item.name.contains("Conjured")) {
            reduceQualityBy(2, items, i);
        }
    }

    private void handleQualityBySellInValueRanges(int i, Item item) {
        if (item.sellIn < 11 && item.sellIn > 5) {
            increaseQualityBy(2, items, i);
        }
        if (item.sellIn < 6) {
            increaseQualityBy(3, items, i);
        }
    }

    private void reduceQualityBy(int amount, Item[] items, int i) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality - amount;
            } else {
                items[i].quality = 49;
            }
    }

    private void increaseQualityBy(int amount, Item[] items, int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + amount;
        } else {
            items[i].quality = 50;
        }
    }

    private void reduceSellInBy(int amount, Item[] items, int i) {
        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].sellIn = items[i].sellIn - amount;
        } else {
            items[i].quality = 50;
        }
    }

    private void handleSulfurasItem(Item[] items, int i) {
        if (items[i].name.contains("Sulfuras")) {
            items[i].quality = 80;
            items[i].sellIn = Integer.MAX_VALUE;
        }
    }
}
