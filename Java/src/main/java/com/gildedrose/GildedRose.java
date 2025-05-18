package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

class GildedRose {
    private static final List<String> increaseQualityItems =
        Arrays.asList("Aged Brie", "Backstage passes to a TAFKAL80ETC concert");
    private static final int MAX_QUALITY_VALUE = 50;
    private static final int MIN_QUALITY_VALUE = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isALegendaryItem(item)) {
                continue;
            }
            item.sellIn -= 1;

            if (shouldIncreaseQuality(item)) {
                updateQuality(item, this::increaseQuality);
                handleBackstagePasses(item);
            } else {
                updateQuality(item, this::decreaseQuality);
            }
        }
    }

    private void updateQuality(Item item, Consumer<Item> updateMethod) {
        updateMethod.accept(item);
        if (isPastSellInDate(item)) {
            updateMethod.accept(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY_VALUE) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY_VALUE) {
            item.quality -= 1;
        }
    }
    private boolean isALegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean shouldIncreaseQuality(Item item) {
        return increaseQualityItems.contains(item.name);
    }

    private boolean isPastSellInDate(Item item) {
        return item.sellIn < 0;
    }

    private void handleBackstagePasses(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 10) {
                increaseQuality(item);
            }
            if (item.sellIn < 5) {
                increaseQuality(item);
            }
            if (isPastSellInDate(item)) {
                item.quality = 0;
            }
        }
    }
}
