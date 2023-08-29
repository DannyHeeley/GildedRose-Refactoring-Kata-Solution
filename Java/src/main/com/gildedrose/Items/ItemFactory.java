package com.gildedrose.Items;

public class ItemFactory {
    public ItemBase newAgedItem(String itemName, int sellInValue, int qualityValue) {
        return new Aged(itemName, sellInValue, qualityValue);
    }
    public ItemBase newBackstagePass(String itemName, int sellInValue, int qualityValue) {
        return new BackstagePass(itemName, sellInValue, qualityValue);
    }
    public ItemBase newConjuredItem(String itemName, int sellInValue, int qualityValue) {
        return new Conjured(itemName, sellInValue, qualityValue);
    }
    public ItemBase newSulfurasItem(String itemName, int sellInValue, int qualityValue) {
        return new Sulfuras(itemName, sellInValue, qualityValue);
    }
    public ItemBase newDefaultItem(String itemName, int sellInValue, int qualityValue) {
        return new ItemBase(itemName, sellInValue, qualityValue);
    }
}
