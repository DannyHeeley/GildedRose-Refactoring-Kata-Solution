package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            updateBasedOnName(i, item);
        }
    }

    private void updateBasedOnName(int i, Item item) {

        //If item isn't sulfuras reduce item sellIn by 1
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        //Update quality by one if item isn't aged, backstage pass or sulfuras
        if (!item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")
            && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                reduceQualityBy(1, items, i);

        //Increase quality if quality is less than 50
        } else {
            increaseQualityIfLessThan50(i, item);
        }

        if (!item.name.equals("Aged Brie")) {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    reduceQualityBy(1, items, i);
                }
            }
        } else {
            increaseQualityBy(1, items, i);
        }
    }

    private void increaseQualityIfLessThan50(int i, Item item) {
        if (item.quality < 50) {
            increaseQualityBy(1, items, i);
            handleBackstagePasses(i, item);
        }
    }

    private void handleBackstagePasses(int i, Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11 && item.sellIn > 5) {
                if (item.quality < 50) {
                    increaseQualityBy(1, items, i);
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    increaseQualityBy(2, items, i);
                }
            }
        }
    }

    private int reduceQualityBy(int amount, Item[] items, int i) {
        return items[i].quality = items[i].quality - amount;
    }
    private int increaseQualityBy(int amount, Item[] items, int i) {
        return items[i].quality = items[i].quality + amount;
    }

}
