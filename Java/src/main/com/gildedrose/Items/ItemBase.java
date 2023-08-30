package com.gildedrose.Items;

public class ItemBase {
    private final String itemName;
    private int sellIn;
    private int quality;
    private final ItemType itemType;

    public ItemBase(String itemName, int sellIn, int quality) {
        this.itemName = itemName;
        this.sellIn = sellIn;
        this.quality = quality;
        this.itemType = ItemType.DEFAULT;
    }

    public ItemBase(String itemName, int sellIn, int quality, ItemType itemType) {
        this.itemName = itemName;
        this.sellIn = sellIn;
        this.quality = quality;
        this.itemType = itemType;
    }

    public void reduceSellInByOne() {
        this.sellIn -= 1;
    }

    public void reduceQualityBy(int amount) {
        if (!this.itemName.contains(String.valueOf(ItemType.SULFURAS))) {
            if (this.quality < 50) {
                this.quality -= amount;
            } else {
                this.quality = 49;
            }
        }
    }

    public void increaseQualityBasedOnRemainingDaysToSell() {
        if (this.sellIn < 11 && this.sellIn > 5) {
            this.increaseQualityBy(2);
        }
        if (this.sellIn < 6) {
            this.increaseQualityBy(3);
        }
    }

    public void increaseQualityBy(int amount) {
        if (this.quality < 50) {
            this.quality += amount;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public ItemType getItemType() {
        return itemType;
    }
    @Override
    public String toString() {
        return itemName + ", " + sellIn + ", " + quality;
    }
}
