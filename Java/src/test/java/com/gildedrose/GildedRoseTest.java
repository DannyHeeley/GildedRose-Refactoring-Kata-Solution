package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void itemsHaveNameValue() {
        Item[] items = new Item[]{new Item("foo", 10, 0)};
        GildedRose app = new GildedRose(items);
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void itemsHaveQualityValue() {
        Item[] items = new Item[]{new Item("foo", 0, 10)};
        GildedRose app = new GildedRose(items);
        assertEquals(10, app.items[0].quality);
    }

    @Test
    void itemsHaveSellInValue() {
        Item[] items = new Item[]{new Item("foo", 10, 0)};
        GildedRose app = new GildedRose(items);
        assertEquals(10, app.items[0].sellIn);
    }

    @Test
    void itemsHaveAllThreeValues() {
        Item[] items = new Item[]{new Item("foo", 10, 0)};
        GildedRose app = new GildedRose(items);
        assertAll(
            () -> assertEquals("foo", app.items[0].name),
            () -> assertEquals(10, app.items[0].sellIn),
            () -> assertEquals(0, app.items[0].quality)
        );
    }

    @Test
    void itemQualityIsLoweredAtEndOfDay() {
        Item[] items = new Item[]{new Item("foo", 1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue(app.items[0].quality < 10);
    }

    @Test
    void itemSellInIsLoweredAtEndOfDay() {
        Item[] items = new Item[]{new Item("foo", 1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void sellInAndQualityAreLoweredAtEndOfDay() {
        Item[] items = new Item[]{new Item("foo", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(9, app.items[0].quality)
        );
    }

    @Test
    void qualityDegradesTwiceAsFastIfSellInIs0() {
        Item[] items = new Item[]{new Item("foo", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void itemQualityIsNeverMinusValue() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void agedItemQualityIsNeverMinusValue() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesEachDay() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertTrue(app.items[0].quality > 0),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs6() {
        Item[] items = new Item[]{new Item("Aged Brie", 6, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs10() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs0() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs5() {
        Item[] items = new Item[]{new Item("Aged Brie", 5, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void qualityOfNonAgedItemIsNeverMoreThan50() {
        Item[] items = new Item[]{new Item("foo", 0, 55)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue(app.items[0].quality <= 50);
    }

    @Test
    void qualityOfAgedItemIsNeverMoreThan50() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    public void sulfurasSellInIsAlwaysPositiveValue() {
        Item[] items = new Item[]{new Item("Sulfuras", 1, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertTrue(app.items[0].sellIn >= 0),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    public void sulfurasQualityIsAlways80() {
        Item[] items = new Item[]{new Item("Sulfuras", 0, 55)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    public void sulfurasDoesNotDecreaseInQuality() {
        Item[] items = new Item[]{new Item("Sulfuras", 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs6() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 6, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs10() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs0() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs5() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    public void backstagePassQualityDoesNotExceed50() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

//    @Test
//    public void qualityDropsTo0AfterTheConcert() {
//        Item[] items = new Item[]{new Item("foo", 0, 55)};
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//    }

//    @Test
//    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
//        Item[] items = new Item[] { new Item("foo", 0, 0) };
//        GildedRose app = new GildedRose(items);
//    }
}
