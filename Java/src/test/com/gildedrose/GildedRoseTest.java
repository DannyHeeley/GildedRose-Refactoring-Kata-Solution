package com.gildedrose;

import com.gildedrose.Items.ItemBase;
import com.gildedrose.Items.ItemFactory;
import com.gildedrose.Strategies.ItemManager;
import com.gildedrose.Strategies.ItemStrategies;
import com.gildedrose.Items.ItemType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void itemsHaveNameValue() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertEquals("Default", app.items[0].getName());
    }

    @Test
    void itemsHaveQualityValue() {
        GildedRose app = handleUpdateQualityDefault(0, 10);
        assertNotNull(app.items[0].getQuality());
    }

    @Test
    void itemsHaveSellInValue() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertNotNull(app.items[0].getSellIn());
    }

    @Test
    void itemsHaveAllThreeValues() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertAll(
            () -> assertNotNull(app.items[0].getName()),
            () -> assertNotNull(app.items[0].getSellIn()),
            () -> assertNotNull(app.items[0].getQuality())
        );
    }

    @Test
    void itemQualityIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(1, 10);
        assertTrue(app.items[0].getQuality() < 10);
    }

    @Test
    void itemSellInIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(1, 10);
        assertEquals(0, app.items[0].getSellIn());
    }

    @Test
    void sellInAndQualityAreLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(10, 10);
        assertAll(
            () -> assertEquals(9, app.items[0].getSellIn()),
            () -> assertEquals(9, app.items[0].getQuality())
        );
    }

    @Test
    void sellInCanBeMinusValue() {
        GildedRose app = handleUpdateQualityDefault(0, 10);
        assertTrue(app.items[0].getSellIn() < 0);
    }

    @Test
    void itemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQualityDefault(0, 0);
        assertEquals(ItemType.DEFAULT, app.items[0].getItemType());
    }

    @Test
    void agedItemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQualityAged(0, -1);
        assertAll(
            () -> assertTrue(app.items[0].getQuality() >= 0),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void agedQualityIncreasesEachDay() {
        GildedRose app = handleUpdateQualityAged(0, 0);
        assertAll(
            () -> assertTrue(app.items[0].getQuality() > 0),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQualityAged(6, 0);
        assertAll(
            () -> assertEquals(2, app.items[0].getQuality()),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQualityAged(10, 0);
        assertAll(
            () -> assertEquals(2, app.items[0].getQuality()),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQualityAged(0, 0);
        assertAll(
            () -> assertEquals(3, app.items[0].getQuality()),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQualityAged(5, 0);
        assertAll(
            () -> assertEquals(3, app.items[0].getQuality()),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void qualityOfNonAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQualityDefault(0, 55);
        assertTrue(app.items[0].getQuality() <= 50);
    }

    @Test
    void qualityOfAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQualityAged(0, 50);
        assertEquals(50, app.items[0].getQuality());
        assertAll(
            () -> assertEquals(50, app.items[0].getQuality()),
            () -> assertEquals(ItemType.AGED, app.items[0].getItemType())
        );
    }

    @Test
    void sulfurasSellInIsAlwaysPositiveValue() {
        GildedRose app = handleUpdateQualitySulfuras(1, 80);
        assertAll(
            () -> assertTrue(app.items[0].getSellIn() >= 0),
            () -> assertEquals(ItemType.SULFURAS, app.items[0].getItemType())
        );
    }

    @Test
    void sulfurasQualityIsAlways80() {
        GildedRose app = handleUpdateQualitySulfuras(0, 55);
        assertAll(
            () -> assertEquals(80, app.items[0].getQuality()),
            () -> assertEquals(ItemType.SULFURAS, app.items[0].getItemType())
        );
    }

    @Test
    void sulfurasDoesNotDecreaseInQuality() {
        GildedRose app = handleUpdateQualitySulfuras(0, 80);
        assertAll(
            () -> assertEquals(80, app.items[0].getQuality()),
            () -> assertEquals(ItemType.SULFURAS, app.items[0].getItemType())
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQualityBackstagePass(6, 0);
        assertAll(
            () -> assertEquals(2, app.items[0].getQuality()),
            () -> assertEquals(ItemType.BACKSTAGE_PASSES, app.items[0].getItemType())
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQualityBackstagePass(10, 0);
        assertAll(
            () -> assertEquals(2, app.items[0].getQuality()),
            () -> assertEquals(ItemType.BACKSTAGE_PASSES, app.items[0].getItemType())
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQualityBackstagePass(0, 0);
        System.out.println(Arrays.toString(app.items));
        assertEquals(3, app.items[0].getQuality());
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQualityBackstagePass(5, 0);
        assertAll(
            () -> assertEquals(3, app.items[0].getQuality()),
            () -> assertEquals(ItemType.BACKSTAGE_PASSES, app.items[0].getItemType())
        );
    }

    @Test
    void backstagePassQualityDoesNotExceed50() {
        GildedRose app = handleUpdateQualityBackstagePass(10, 50);
        assertAll(
            () -> assertEquals(50, app.items[0].getQuality()),
            () -> assertEquals(ItemType.BACKSTAGE_PASSES, app.items[0].getItemType())
        );
    }

    @Test
    void qualityDegradesTwiceAsFastIfItemIsExpired() {
        GildedRose app = handleUpdateQualityDefault(-1, 10);
        assertEquals(8, app.items[0].getQuality());
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        GildedRose app = handleUpdateQualityConjured(10, 10);
        assertEquals(8, app.items[0].getQuality());
    }

    @Test
    void returnsTrueWhenConcertHasEnded() {
        Concert concert = new Concert("Madness", "01/07/2023", "13:00:00");
        assertTrue(concert.isConcertOver(new GetDateTime()));
    }

    @Test
    void qualityDropsTo0AfterTheConcert() {
        Concert concert = new Concert("Madness", "01/07/2023", "13:00:00");
        GildedRose app = handleUpdateQualityConcert(0, 10, "foo", concert);
        assertAll (
            () -> assertTrue(concert.isConcertOver(new GetDateTime())),
            () -> assertEquals(0, app.items[0].getQuality())
        );
    }

    private GildedRose handleUpdateQualityDefault(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{itemFactory.newDefaultItem("Default", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager);
        app.updateQuality();
        return app;
    }
    private GildedRose handleUpdateQualityAged(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{itemFactory.newAgedItem("Aged item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualityBackstagePass(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{itemFactory.newBackstagePass("Backstage Pass Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualityConjured(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{itemFactory.newConjuredItem("Conjured Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualitySulfuras(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{itemFactory.newSulfurasItem("Sulfuras Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager);
        app.updateQuality();
        return app;
    }
    private GildedRose handleUpdateQualityConcert(int sellInValue, int qualityValue, String itemName, Concert concert) {
        ItemManager itemManager = new ItemManager();
        ItemStrategies itemStrategies = new ItemStrategies(itemManager);
        ItemBase[] items = new ItemBase[]{new ItemBase(itemName, sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, itemManager, concert);
        app.updateQuality();
        return app;
    }
}
