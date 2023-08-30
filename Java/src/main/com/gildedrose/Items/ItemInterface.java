package com.gildedrose.Items;

public interface ItemInterface {
    String toString();
    void setQuality(int quality);
    int getQuality();
    void setSellIn(int sellIn);
    int getSellIn();
    String getItemName();
    void increaseQualityBy(int amount);
    void increaseQualityBasedOnRemainingDaysToSell();
    void reduceQualityBy(int amount);
    void reduceSellInByOne();
}
