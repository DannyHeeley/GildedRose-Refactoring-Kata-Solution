package com.gildedrose.Items;

public class ItemFactory {
    public Item newAgedItem(String itemName, int sellInValue, int qualityValue) {
        return new Aged(itemName, sellInValue, qualityValue);
    }
    public Item newBackstagePass(String itemName, int sellInValue, int qualityValue) {
        return new BackstagePass(itemName, sellInValue, qualityValue);
    }
    public Item newConjuredItem(String itemName, int sellInValue, int qualityValue) {
        return new Conjured(itemName, sellInValue, qualityValue);
    }
    public Item newSulfurasItem(String itemName, int sellInValue, int qualityValue) {
        return new Sulfuras(itemName, sellInValue, qualityValue);
    }
    public Item newDefaultItem(String itemName, int sellInValue, int qualityValue) {
        return new Item(itemName, sellInValue, qualityValue);
    }
}
