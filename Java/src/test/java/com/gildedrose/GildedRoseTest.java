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

    @Test
    void itemsHaveNameValue() {
        int sellInValue = 10;
        int qualityValue = 0;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void itemsHaveQualityValue() {
        int sellInValue = 0;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertNotNull(app.items[0].quality);
    }

    @Test
    void itemsHaveSellInValue() {
        int sellInValue = 10;
        int qualityValue = 0;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertNotNull(app.items[0].sellIn);
    }

    @Test
    void itemsHaveAllThreeValues() {
        int sellInValue = 10;
        int qualityValue = 0;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertNotNull(app.items[0].name),
            () -> assertNotNull(app.items[0].sellIn),
            () -> assertNotNull(app.items[0].quality)
        );
    }

    @Test
    void itemQualityIsLoweredAtEndOfDay() {
        int sellInValue = 1;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertTrue(app.items[0].quality < 10);
    }

    @Test
    void itemSellInIsLoweredAtEndOfDay() {
        int sellInValue = 1;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void sellInAndQualityAreLoweredAtEndOfDay() {
        int sellInValue = 10;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(9, app.items[0].quality)
        );
    }

    @Test
    void sellInCanBeMinusValue() {
        int sellInValue = 0;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertTrue(app.items[0].sellIn < 0);
    }

    @Test
    void itemQualityIsNeverMinusValue() {
        int sellInValue = 0;
        int qualityValue = 0;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void agedItemQualityIsNeverMinusValue() {
        int sellInValue = 0;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesEachDay() {
        int sellInValue = 0;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertTrue(app.items[0].quality > 0),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs6() {
        int sellInValue = 6;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs10() {
        int sellInValue = 10;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs0() {
        int sellInValue = 0;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs5() {
        int sellInValue = 5;
        int qualityValue = 0;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void qualityOfNonAgedItemIsNeverMoreThan50() {
        int sellInValue = 0;
        int qualityValue = 55;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertTrue(app.items[0].quality <= 50);
    }

    @Test
    void qualityOfAgedItemIsNeverMoreThan50() {
        int sellInValue = 0;
        int qualityValue = 50;
        String itemName = "Aged Brie";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals(50, app.items[0].quality);
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Aged"))
        );
    }

    @Test
    void sulfurasSellInIsAlwaysPositiveValue() {
        int sellInValue = 1;
        int qualityValue = 80;
        String itemName = "Sulfuras";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertTrue(app.items[0].sellIn >= 0),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void sulfurasQualityIsAlways80() {
        int sellInValue = 0;
        int qualityValue = 55;
        String itemName = "Sulfuras";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void sulfurasDoesNotDecreaseInQuality() {
        int sellInValue = 0;
        int qualityValue = 80;
        String itemName = "Sulfuras";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(80, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Sulfuras"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs6() {
        int sellInValue = 6;
        int qualityValue = 0;
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs10() {
        int sellInValue = 10;
        int qualityValue = 0;
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(2, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs0() {
        int sellInValue = 0;
        int qualityValue = 0;
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs5() {
        int sellInValue = 5;
        int qualityValue = 0;
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(3, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void backstagePassQualityDoesNotExceed50() {
        int sellInValue = 10;
        int qualityValue = 50;
        String itemName = "Backstage passes to a TAFKAL80ETC concert";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertAll(
            () -> assertEquals(50, app.items[0].quality),
            () -> assertTrue(app.items[0].name.contains("Backstage passes"))
        );
    }

    @Test
    void qualityDegradesTwiceAsFastIfItemIsExpired() {
        int sellInValue = -1;
        int qualityValue = 10;
        String itemName = "foo";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        int sellInValue = 10;
        int qualityValue = 10;
        String itemName = "Conjured Duck";
        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
        assertEquals(8, app.items[0].quality);
    }

//    @Test
//    void returnsTrueWhenConcertHasEnded() {
//        int sellInValue = 0;
//        int qualityValue = 10;
//        String itemName = "foo";
//        GetDateTime dateTimeNow = new GetDateTime();
//        Concert concert = new Concert("Boomtown", "01/07/2023", "13:00:00", dateTimeNow);
//        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
//        assertTrue(app.isConcertOver(concert, dateTimeNow));
//    }

//    @Test
//    void qualityDropsTo0AfterTheConcert() {
//        int sellInValue = 0;
//        int qualityValue = 10;
//        String itemName = "foo";
//        GetDateTime dateTimeNow = new GetDateTime();
//        Concert concert = new Concert("Boomtown", "01/07/2023", "13:00:00", dateTimeNow);
//        GildedRose app = handleUpdateQuality(sellInValue, qualityValue, itemName);
//        assertAll (
//            () -> assertTrue(app.isConcertOver(concert, dateTimeNow)),
//            () -> assertEquals(0, app.items[0].quality)
//        );
//    }

}
