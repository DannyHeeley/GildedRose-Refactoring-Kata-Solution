package com.gildedrose;

import com.gildedrose.Core.Concert;
import com.gildedrose.Core.GetDateTime;
import com.gildedrose.Core.GildedRose;
import com.gildedrose.Items.ItemInterface;
import com.gildedrose.Items.ItemFactory;
import com.gildedrose.ItemStrategies.ItemStrategies;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void itemsHaveNameValue() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertEquals("Default", app.getItems()[0].getItemName());
    }

    @Test
    void itemsHaveQualityValue() {
        GildedRose app = handleUpdateQualityDefault(0, 10);
        assertNotNull(app.getItems()[0].getQuality());
    }

    @Test
    void itemsHaveSellInValue() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertNotNull(app.getItems()[0].getSellIn());
    }

    @Test
    void itemsHaveAllThreeValues() {
        GildedRose app = handleUpdateQualityDefault(10, 0);
        assertAll(
            () -> assertNotNull(app.getItems()[0].getItemName()),
            () -> assertNotNull(app.getItems()[0].getSellIn()),
            () -> assertNotNull(app.getItems()[0].getQuality())
        );
    }

    @Test
    void itemQualityIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(1, 10);
        assertTrue(app.getItems()[0].getQuality() < 10);
    }

    @Test
    void itemSellInIsLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(1, 10);
        assertEquals(0, app.getItems()[0].getSellIn());
    }

    @Test
    void sellInAndQualityAreLoweredAtEndOfDay() {
        GildedRose app = handleUpdateQualityDefault(10, 10);
        assertAll(
            () -> assertEquals(9, app.getItems()[0].getSellIn()),
            () -> assertEquals(9, app.getItems()[0].getQuality())
        );
    }

    @Test
    void sellInCanBeMinusValue() {
        GildedRose app = handleUpdateQualityDefault(0, 10);
        assertTrue(app.getItems()[0].getSellIn() < 0);
    }

    @Test
    void itemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQualityDefault(0, 0);
        System.out.println(app.getItems()[0].getQuality());
        assertTrue(app.getItems()[0].getQuality() >= 0);
    }

    @Test
    void agedItemQualityIsNeverMinusValue() {
        GildedRose app = handleUpdateQualityAged(0, -1);
        assertTrue(app.getItems()[0].getQuality() >= 0);
    }

    @Test
    void agedQualityIncreasesEachDay() {
        GildedRose app = handleUpdateQualityAged(0, 0);
        assertTrue(app.getItems()[0].getQuality() > 0);
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQualityAged(6, 0);
        assertEquals(2, app.getItems()[0].getQuality());
    }

    @Test
    void agedQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQualityAged(10, 0);
        assertEquals(2, app.getItems()[0].getQuality());
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQualityAged(0, 0);
        assertEquals(3, app.getItems()[0].getQuality());
    }

    @Test
    void agedQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQualityAged(5, 0);
        assertEquals(3, app.getItems()[0].getQuality());
    }

    @Test
    void qualityOfNonAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQualityDefault(0, 55);
        assertTrue(app.getItems()[0].getQuality() <= 50);
    }

    @Test
    void qualityOfAgedItemIsNeverMoreThan50() {
        GildedRose app = handleUpdateQualityAged(0, 50);
        assertEquals(50, app.getItems()[0].getQuality());
    }

    @Test
    void sulfurasSellInIsAlwaysPositiveValue() {
        GildedRose app = handleUpdateQualitySulfuras(1, 80);
        assertTrue(app.getItems()[0].getSellIn() >= 0);
    }

    @Test
    void sulfurasQualityIsAlways80() {
        GildedRose app = handleUpdateQualitySulfuras(0, 55);
        assertEquals(80, app.getItems()[0].getQuality());
    }

    @Test
    void sulfurasDoesNotDecreaseInQuality() {
        GildedRose app = handleUpdateQualitySulfuras(0, 80);
        assertEquals(80, app.getItems()[0].getQuality());
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs6() {
        GildedRose app = handleUpdateQualityBackstagePass(6, 0);
        assertEquals(2, app.getItems()[0].getQuality());
    }

    @Test
    void backstagePassQualityIncreasesBy2IfDaysToSellIs10() {
        GildedRose app = handleUpdateQualityBackstagePass(10, 0);
        assertEquals(2, app.getItems()[0].getQuality());
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs0() {
        GildedRose app = handleUpdateQualityBackstagePass(0, 0);
        assertEquals(3, app.getItems()[0].getQuality());
    }

    @Test
    void backstagePassQualityIncreasesBy3IfDaysToSellIs5() {
        GildedRose app = handleUpdateQualityBackstagePass(5, 0);
        assertEquals(3, app.getItems()[0].getQuality());
    }

    @Test
    void backstagePassQualityDoesNotExceed50() {
        GildedRose app = handleUpdateQualityBackstagePass(10, 50);
        assertEquals(50, app.getItems()[0].getQuality());
    }

    @Test
    void qualityDegradesTwiceAsFastIfItemIsExpired() {
        GildedRose app = handleUpdateQualityDefault(-1, 10);
        assertEquals(8, app.getItems()[0].getQuality());
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        GildedRose app = handleUpdateQualityConjured(10, 10);
        assertEquals(8, app.getItems()[0].getQuality());
    }

    @Test
    void returnsTrueWhenConcertHasEnded() {
        Concert concert = new Concert("Madness", "01/07/2023", "13:00:00");
        assertTrue(concert.isConcertOver(new GetDateTime()));
    }

    @Test
    void qualityDropsTo0AfterTheConcert() {
        Concert concert = new Concert("Madness", "01/07/2023", "13:00:00");
        GildedRose app = handleUpdateQualityConcert(0, 10, concert);
        assertAll (
            () -> assertTrue(concert.isConcertOver(new GetDateTime())),
            () -> assertEquals(0, app.getItems()[0].getQuality())
        );
    }

    private GildedRose handleUpdateQualityDefault(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newDefaultItem("Default", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies);
        app.updateQuality();
        return app;
    }
    private GildedRose handleUpdateQualityAged(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newAgedItem("Aged item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualityBackstagePass(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newBackstagePass("Backstage Pass Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualityConjured(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newConjuredItem("Conjured Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies);
        app.updateQuality();
        return app;
    }

    private GildedRose handleUpdateQualitySulfuras(int sellInValue, int qualityValue) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newSulfurasItem("Sulfuras Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies);
        app.updateQuality();
        return app;
    }
    private GildedRose handleUpdateQualityConcert(int sellInValue, int qualityValue, Concert concert) {
        ItemFactory itemFactory = new ItemFactory();
        ItemStrategies itemStrategies = new ItemStrategies();
        ItemInterface[] items = new ItemInterface[]{itemFactory.newDefaultItem("Default Item", sellInValue, qualityValue)};
        GildedRose app = new GildedRose(items, itemStrategies, concert);
        app.updateQuality();
        return app;
    }
}
