package com.pizzisalle.utils;

import java.util.HashMap;
import java.util.Map;

public class IngredientPriceList {
    private static final Map<String, Double> prices = new HashMap<>();

    static {
        prices.put("Cheese", 1.0);
        prices.put("Mushrooms", 0.5);
        prices.put("Pepperoni", 0.75);
        prices.put("Ham", 0.75);
        prices.put("Pineapple", 0.5);
        prices.put("Chicken", 1.0);
        prices.put("Beef", 1.0);
        prices.put("Bacon", 0.75);
        prices.put("Onion", 0.5);
        prices.put("Olives", 0.5);
        prices.put("BBQ Sauce", 0.5);
        prices.put("Prawns", 1.5);
        prices.put("Tuna", 1.0);
        prices.put("Brie", 1.0);
    }

    public static double getPrice(String ingredient) {
        return prices.getOrDefault(ingredient, 0.0);
    }
}