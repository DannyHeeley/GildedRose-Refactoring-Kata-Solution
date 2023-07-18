//package com.gildedrose;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SpecialCaseItemStrategies implements SpecialCaseItems {
//    private final Map<String, Void> specialCaseItemMap = new HashMap<>();
//
//    public SpecialCaseItemStrategies(Item item) {
//        specialCaseItemMap.put(AGED, handleAgedItem(i, item));
//        specialCaseItemMap.put(SULFURAS, handleSulfurasItem(items, i));
//        specialCaseItemMap.put(BACKSTAGE_PASSES, handleBackstagePassItem(i, item));
//        specialCaseItemMap.put(CONJURED, handleConjuredItem(i, item));
//    }
//
//
//    private Boolean itemIsSpecialCase(Item item) {
//        return specialCaseItemMap.containsKey(item.name);
//    }
//}
