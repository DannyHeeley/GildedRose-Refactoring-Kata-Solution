package com.gildedrose;

/** @noinspection SpellCheckingInspection*/
class GildedRose implements specialCaseItems {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            handleItemDefault(i, item);
            handleAgedItem(i, item);
            handleBackstagePassItem(i, item);
            handleSulfurasItem(items, i);
            handleConjuredItem(i, item);
            handleExpiredItem(i, item);
            reduceSellInBy(1, items, i);
            //handleItemsIfConcertHasEnded(i);
        }
    }

//    public boolean isConcertOver(Concert concert, GetDateTime dateTimeNow) {
//        if (dateTimeNow.getLocalDate() < concert.concertDate) {
//            if (dateTimeNow.getLocalTime() < concert.concertEndTime) {
//                return false;
//            }
//        }
//        return true;
//    }
//    private void handleItemsIfConcertHasEnded(int i) {
//        GetDateTime dateTime = new GetDateTime();
//        // Hardcoded line, needs fix
//        Concert concert = new Concert("Boomtown", "14/07/2023", "13:00:00", dateTime);
//        if (isConcertOver(concert, dateTime)) {
//            setQualityToZero(items, i);
//        }
//    }
    private void handleItemDefault(int i, Item item) {
        if (!item.name.contains(AGED)
            && !item.name.contains(BACKSTAGE_PASSES)
            && !item.name.contains(SULFURAS)
            && !item.name.contains(CONJURED)
            && item.sellIn > -1)
        {
            reduceQualityBy(1, items, i);
        }
    }

    private void handleAgedItem(int i, Item item) {
        if (item.name.contains(AGED)) {
            increaseQualityBySellInValueRange(i, item);
        }
    }

    private void handleBackstagePassItem(int i, Item item) {
        if (item.name.contains(BACKSTAGE_PASSES)) {
            increaseQualityBySellInValueRange(i, item);
        }
    }

    private void handleSulfurasItem(Item[] items, int i) {
        if (items[i].name.contains(SULFURAS)) {
            items[i].quality = 80;
            items[i].sellIn = Integer.MAX_VALUE;
        }
    }

    private void handleConjuredItem(int i, Item item) {
        if (item.name.contains(CONJURED)) {
            reduceQualityBy(2, items, i);
        }
    }

    private void handleExpiredItem(int i, Item item) {
        if (!item.name.contains(AGED)
            && !item.name.contains(BACKSTAGE_PASSES)
            && !item.name.contains(SULFURAS)
            && !item.name.contains(CONJURED)
            && item.sellIn < 0)
        {
            reduceQualityBy(2, items, i);
        }
    }

    private void increaseQualityBySellInValueRange(int i, Item item) {
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
        }
    }

    private void reduceSellInBy(int amount, Item[] items, int i) {
            items[i].sellIn = items[i].sellIn - amount;
    }

    private void setQualityToZero(Item[] items, int i) {
        if (!items[i].name.contains(SULFURAS)) {
            items[i].quality = 0;
        }
    }

}
