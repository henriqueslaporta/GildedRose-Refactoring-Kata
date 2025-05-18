package com.gildedrose;

class GildedRose {
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

            if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        decreaseQuality(item);
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    increaseQuality(item);
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
}
