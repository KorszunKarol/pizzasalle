package com.pizzisalle.service;

import java.util.HashMap;
import java.util.Map;

public class IngredientPriceList {
    private static final Map<String, Double> prices = new HashMap<>();

    static {
        prices.put("Cheese", 1.00);
        prices.put("Tomato Sauce", 0.50);
        prices.put("Ham", 0.75);
        prices.put("Chicken", 1.00);
        prices.put("Beef", 1.25);
        prices.put("Bacon", 0.75);
        prices.put("Pepperoni", 0.75);
        prices.put("Frankfurt", 0.75);
        prices.put("Miniburgers", 1.50);
        prices.put("Tuna", 1.00);
        prices.put("Anchovies", 1.00);
        prices.put("Prawns", 1.50);
        prices.put("Onion", 0.50);
        prices.put("Mushrooms", 0.50);
        prices.put("Bell Pepper", 0.50);
        prices.put("Tomato slices", 0.50);
        prices.put("Artichoke", 0.75);
        prices.put("Olives", 0.50);
        prices.put("Mozzarella", 1.00);
        prices.put("Goat Cheese", 1.25);
        prices.put("Brie", 1.25);
        prices.put("Emmental", 1.25);
        prices.put("Roquefort", 1.25);
        prices.put("Cheddar", 1.00);
        prices.put("BBQ Sauce", 0.50);
        prices.put("Carbonara sauce", 0.75);
        prices.put("Pineapple", 0.50);
        prices.put("Egg", 0.50);
        prices.put("Jamón Serrano", 1.50);
        prices.put("Sobrassada", 1.25);
        prices.put("Honey", 0.50);
    }

    public static double getPrice(String ingredient) {
        return prices.getOrDefault(ingredient, 0.0);
    }
}