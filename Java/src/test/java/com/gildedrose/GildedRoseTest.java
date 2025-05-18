package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void shouldDecreaseSellInAndQualityOfAItemAfterOneDay() {
        Item[] items = new Item[] {
            new Item("Random", 2, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(19, items[0].quality);
        assertEquals(1, items[0].sellIn);
    }

    @Test
    void shouldDecreaseByTwoTheQualityOfAItemWithPastSellIn() {
        Item[] items = new Item[] {
            new Item("Random", 0, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(18, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void shouldQualityNeverBeNegative() {
        Item[] items = new Item[] {
            new Item("Random", 0, 1),
            new Item("Random", 2, 0)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[1].quality);
        assertEquals(1, items[1].sellIn);
    }

    @Test
    void shouldAgedBrieIncreaseQualityWhenGetOlder() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 1, 1),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(2, items[0].quality);
        assertEquals(0, items[0].sellIn);
    }

    @Test
    void shouldQualityNeverBeGreaterThan50() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 1, 50),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, items[0].quality);
        assertEquals(0, items[0].sellIn);
    }

    @Test
    void shouldNotUpdateSellInAndQualityWhenItemIsEqualToSulfuras() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(80, items[0].quality);
        assertEquals(1, items[0].sellIn);
    }

    @Test
    void shouldBackstagePassesIncreaseQualityWhenGetOlder() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 1),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(2, items[0].quality);
        assertEquals(14, items[0].sellIn);
        assertEquals(2, items[1].quality);
        assertEquals(10, items[1].sellIn);
    }

    @Test
    void shouldBackstagePassesIncreaseQualityByTwoWhenSellInIsTenOrLess() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(3, items[0].quality);
        assertEquals(9, items[0].sellIn);
        assertEquals(3, items[1].quality);
        assertEquals(5, items[1].sellIn);
    }

    @Test
    void shouldBackstagePassesIncreaseQualityByThreeWhenSellInIsTenOrLess() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(4, items[0].quality);
        assertEquals(4, items[0].sellIn);
        assertEquals(4, items[1].quality);
        assertEquals(0, items[1].sellIn);
    }

    @Test
    void shouldBackstagePassesQualityEqualsToZeroAfterSellInDate() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[1].quality);
        assertEquals(-2, items[1].sellIn);
    }

    @Test
    void shouldConjuredItemDegradeTwiceFastThanNormalItems() {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 5, 10),
            new Item("Conjured Mana Cake", -1, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(8, items[0].quality);
        assertEquals(4, items[0].sellIn);
        assertEquals(6, items[1].quality);
        assertEquals(-2, items[1].sellIn);
    }

}
