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
        }
    }

    private void updateItemsBasedOnName(int i, Item item) {

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {

            item.sellIn = item.sellIn - 1;

        }

        if (!item.name.equals("Aged Brie")
            && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")
            && !item.name.equals("Sulfuras, Hand of Ragnaros")) {

                reduceQualityBy(1, items, i);

        } else {
            increaseQualityBy(1, items, i);
            handleBackstagePasses(i, item);
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

    private void handleBackstagePasses(int i, Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11 && item.sellIn > 5) {
                increaseQualityBy(1, items, i);
            }
            if (item.sellIn < 6) {
                increaseQualityBy(1, items, i);
            }
        }
    }

    private int reduceQualityBy(int amount, Item[] items, int i) {
        if (items[i].quality <= 50) {
            return items[i].quality = items[i].quality - amount;
        } else return items[i].quality;
    }

    private int increaseQualityBy(int amount, Item[] items, int i) {
        if (items[i].quality <= 50) {
            return items[i].quality = items[i].quality + amount;
        } else return items[i].quality;
    }

}
