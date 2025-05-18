package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {
    private static final List<String> increaseQualityItems =
        Arrays.asList("Aged Brie", "Backstage passes to a TAFKAL80ETC concert");

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isALegendaryItem(item)) {
                continue;
            }
            item.sellIn = item.sellIn - 1;

            if (!shouldIncreaseQuality(item)) {
                decreaseQuality(item);
            } else {
                increaseQuality(item);
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 10) {
                        increaseQuality(item);
                    }
                    if (item.sellIn < 5) {
                        increaseQuality(item);
                    }
                }
            }

            if (item.sellIn < 0) {
                if (!shouldIncreaseQuality(item)) {
                    decreaseQuality(item);
                } else {
                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        item.quality = 0;
                    } else {
                        increaseQuality(item);
                    }
                }
            }
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
    private boolean isALegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean shouldIncreaseQuality(Item item) {
        return increaseQualityItems.contains(item.name);
    }
}
