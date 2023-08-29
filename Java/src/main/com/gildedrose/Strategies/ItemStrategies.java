package com.gildedrose.Strategies;

import com.gildedrose.GildedRose;
import com.gildedrose.Items.Item;
import com.gildedrose.Items.ItemType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class ItemStrategies {

    Map<ItemType, Consumer<Item>> strategies;

    public ItemStrategies() {
        strategies = new HashMap<>();
        strategies.put(ItemType.AGED, this::handleAgedItem);
        strategies.put(ItemType.SULFURAS, this::handleSulfurasItem);
        strategies.put(ItemType.BACKSTAGE_PASSES, this::handleBackstagePassItem);
        strategies.put(ItemType.CONJURED, this::handleConjuredItem);
    }

    public void useStrategyFor(Item item) {
        Consumer<Item> strategy = getStrategy(item.getItemType());
        if (strategy != null) {
            strategy.accept(item);
        } else {
            handleItemDefault(item);
            handleExpiredItem(item);
        }
    }

    private Consumer<Item> getStrategy(ItemType itemType) {
        return strategies.get(itemType);
    }

    public boolean itemHasStrategy(ItemType itemType) {
        return strategies.containsKey(itemType);
    }

    private void handleItemDefault(Item item) {
        if (item.getSellIn() > -1)
        {
            GildedRose.reduceQualityBy(1, item);
        }
    }

    private void handleAgedItem(Item item) {
        if (item.getItemType().equals(ItemType.AGED)) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }

    private void handleBackstagePassItem(Item item) {
        if (item.getItemType().equals(ItemType.BACKSTAGE_PASSES)) {
            increaseQualityBasedOnRemainingDaysToSell(item);
        }
    }

    private void handleSulfurasItem(Item item) {
        if (item.getItemType().equals(ItemType.SULFURAS)) {
            item.setQuality(80);
            item.setSellIn(Integer.MAX_VALUE);
        }
    }

    private void handleConjuredItem(Item item) {
        if (item.getItemType().equals(ItemType.CONJURED)) {
            GildedRose.reduceQualityBy(2, item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (!itemHasStrategy(item.getItemType())
            && item.getSellIn() < 0)
        {
            GildedRose.reduceQualityBy(2, item);
        }
    }

    private void increaseQualityBasedOnRemainingDaysToSell(Item item) {
        if (item.getSellIn() < 11 && item.getSellIn() > 5) {
            increaseQualityBy(2, item);
        }
        if (item.getSellIn() < 6) {
            increaseQualityBy(3, item);
        }
    }
    public void increaseQualityBy(int amount, Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + amount);
        }
    }
}
