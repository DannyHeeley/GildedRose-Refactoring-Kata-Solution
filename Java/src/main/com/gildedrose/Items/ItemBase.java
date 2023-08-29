package com.gildedrose.Items;

public class ItemBase {
    private String name;
    private int sellIn;
    private int quality;
    private ItemType itemType;

    public ItemBase(String name, int sellIn, int quality) {
        this.setName(name);
        this.setSellIn(sellIn);
        this.setQuality(quality);
        this.itemType = ItemType.DEFAULT;
    }

    public ItemBase(String name, int sellIn, int quality, ItemType itemType) {
        this.setName(name);
        this.setSellIn(sellIn);
        this.setQuality(quality);
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getSellIn() + ", " + this.getQuality();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
