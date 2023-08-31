package com.gildedrose.Items;

public class ItemBase implements ItemInterface {
    private final String itemName;
    private int sellIn;
    private int quality;
    public ItemBase(String itemName, int sellIn, int quality) {
        this.itemName = itemName;
        this.sellIn = sellIn;
        this.quality = quality;
    }
    public void reduceSellInByOne() {
        this.sellIn -= 1;
    }
    public void reduceQualityBy(int amount) {
        if (!(this instanceof Sulfuras) && (quality - amount >= 0)) {
                if (this.quality < 50) {
                    this.quality -= amount;
                } else {
                    this.quality = 49;
                }

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
    @Override
    public String toString() {
        return itemName + ", " + sellIn + ", " + quality;
    }
}
