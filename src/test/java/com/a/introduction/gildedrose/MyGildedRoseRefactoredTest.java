package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyGildedRoseRefactoredTest {

	public static final int NOT_EXPIRED_SELL_IN = 15;
	public static final int DEFAULT_QUALITY = 3;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELL_IN = -1;
	public static final String AGED_BRIE_ITEM = "Aged Brie";
	public static final int MAXIMUM_QUALITY = 50;
	public static final int SELL_IN_GREATER_THAN_10 = 15;
	public static final String BACKSTAGE_PASSES_ITEM = "Backstage passes to a TAFKAL80ETC concert";
	public static final int SELL_IN_BETWEEN_5_AND_10 = 7;
	public static final int SELL_IN_POSITIVE_LESS_THAN_5 = 4;

	@Test
	public void unexpiredItem_qualityDecreasesBy1() {
		
		//setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		//invoke
		app.updateQuality();
		
		//verify
		Item expected = new Item(DEFAULT_ITEM,NOT_EXPIRED_SELL_IN - 1,DEFAULT_QUALITY - 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredItem_qualityDecreasesBy2() {

		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(DEFAULT_ITEM,EXPIRED_SELL_IN - 1,DEFAULT_QUALITY - 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredAgedBrie_qualityIncreasesBy1() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE_ITEM,NOT_EXPIRED_SELL_IN - 1,DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredAgedBrie_qualityIncreasesBy2() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE_ITEM,EXPIRED_SELL_IN - 1,DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredAgedBrie_qualityDoesNotExceedMaximum() {

		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_ITEM, NOT_EXPIRED_SELL_IN, MAXIMUM_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE_ITEM,NOT_EXPIRED_SELL_IN - 1,MAXIMUM_QUALITY);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassesBeyond10Days_qualityIncreasesBy1() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_ITEM, SELL_IN_GREATER_THAN_10, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_ITEM,SELL_IN_GREATER_THAN_10 - 1,DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassesBetween5And10Days_qualityIncreasesBy2() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_ITEM, SELL_IN_BETWEEN_5_AND_10, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_ITEM,SELL_IN_BETWEEN_5_AND_10 - 1,DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassesPositiveLessThan5_qualityIncreasesBy3() {

		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_ITEM, SELL_IN_POSITIVE_LESS_THAN_5, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_ITEM,SELL_IN_POSITIVE_LESS_THAN_5 - 1,DEFAULT_QUALITY + 3);
		assertItem(expected, app.items[0]);
	}

	private static GildedRose createGildedRoseWithOneItem(String itemType, int sellIn, int quality) {
		Item item = new Item(itemType, sellIn, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

	private static void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}
}