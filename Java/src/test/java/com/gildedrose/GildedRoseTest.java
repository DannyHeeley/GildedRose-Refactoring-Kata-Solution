package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    private GildedRose handleUpdateQuality(int sellInValue, int qualityValue, String itemName) {
        Item[] items = new Item[]{new Item(itemName, sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualityConcert(int sellInValue, int qualityValue, String itemName, Concert concert) {
        Item[] items = new Item[]{new Item(itemName, sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, concert);
        app.updateQuality();
        return app;
    }

    @Test
    void itemsHaveNameValue() {
        GildedRose app = handleUpdateQuality(10, 0, "foo");
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void itemsHaveQualityValue() {
        GildedRose app = handleUpdateQuality(0, 10, "foo");
        assertNotNull(app.items[0].quality);
    }

    @Test
    void itemsHaveSellInValue() {
        GildedRose app = handleUpdateQuality(10, 0, "foo");
        assertNotNull(app.items[0].sellIn);
    }

    @Test
    void itemsHaveAllThreeValues() {
        GildedRose app = handleUpdateQuality(10, 0, "foo");
        assertAll(
            () -> assertNotNull(app.items[0].name),
            () -> assertNotNull(app.items[0].sellIn),
            () -> assertNotNull(app.items[0].quality)
        );
    }

    @Test
    void itemQualityIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQuality(1, 10, "foo");
        assertTrue(app.items[0].quality < 10);
    }

    @Test
    void itemSellInIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQuality(1, 10, "foo");
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void sellInAndQualityAreLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQuality(10, 10, "foo");
        assertAll(
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(9, app.items[0].quality)
        );
    }

    @Test
    void sellInCanBeMinusValue() {
        GildedRose app = handleUpdateQuality(0, 10, "foo");
        assertTrue(app.items[0].sellIn < 0);
    }

    @Test
    void itemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQuality(0, 0, "foo");
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void agedItemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQuality(0, 0, "Aged Brie");
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesEachDay() {
        GildedRose app = handleUpdateQuality(0, 0, "Aged Brie");
        assertAll(
            () -> assertTrue(app.items[0].quality > 0),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQuality(6, 0, "Aged Brie");
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQuality(10, 0, "Aged Brie");
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQuality(0, 0, "Aged Brie");
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQuality(5, 0, "Aged Brie");
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void qualityOfNonAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQuality(0, 55, "foo");
        assertTrue(app.items[0].quality <= 50);
    }

    @Test
    void qualityOfAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQuality(0, 50, "Aged Brie");
        assertEquals(50, app.items[0].quality);
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void sulfurasSellInIsAlwaysPositiveValue() {
        GildedRose app = handleUpdateQuality(1, 80, "Sulfuras");
        assertAll(
            () -> assertTrue(app.items[0].sellIn >= 0),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void sulfurasQualityIsAlways80() {
        GildedRose app = handleUpdateQuality(0, 55, "Sulfuras");
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void sulfurasDoesNotDecreaseInQuality() {
        GildedRose app = handleUpdateQuality(0, 80, "Sulfuras");
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQuality(6, 0, "Backstage passes to a TAFKAL80ETC concert");
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQuality(10, 0, "Backstage passes to a TAFKAL80ETC concert");
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQuality(0, 0, "Backstage passes to a TAFKAL80ETC concert");
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQuality(5, 0, "Backstage passes to a TAFKAL80ETC concert");
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityDoesNotExceed50() {
        GildedRose app = handleUpdateQuality(10, 50, "Backstage passes to a TAFKAL80ETC concert");
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void qualityDegradesTwiceAsFastIfItemIsExpired() {
        GildedRose app = handleUpdateQuality(-1, 10, "foo");
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        GildedRose app = handleUpdateQuality(10, 10, "Conjured Duck");
        assertEquals(8, app.items[0].quality);
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
            () -> assertEquals(0, app.items[0].quality)
        );
    }

}
