package com.pizzisalle.service;

import com.pizzisalle.model.pizza.ingredient.Ingredient;
import com.pizzisalle.constants.IngredientPrices;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * PATTERN: Singleton
 * This class manages the available ingredients and their prices.
 * It ensures consistent ingredient pricing across the application.
 */
public class IngredientFactory {
    private static IngredientFactory instance;
    private final Map<String, Ingredient> ingredients;

    private IngredientFactory() {
        ingredients = new HashMap<>();
        initializeIngredients();
    }

    public static IngredientFactory getInstance() {
        if (instance == null) {
            instance = new IngredientFactory();
        }
        return instance;
    }

    private void initializeIngredients() {
        addIngredient("Tomato sauce", IngredientPrices.TOMATO_SAUCE);
        addIngredient("Cheese", IngredientPrices.CHEESE);
        addIngredient("Ham", IngredientPrices.HAM);
        addIngredient("Chicken", IngredientPrices.CHICKEN);
        addIngredient("Bacon", IngredientPrices.BACON);
        addIngredient("Beef", IngredientPrices.BEEF);
        addIngredient("Frankfurt", IngredientPrices.FRANKFURT);
        addIngredient("Pepperoni", IngredientPrices.PEPPERONI);
        addIngredient("Jamón Serrano", IngredientPrices.JAMON_SERRANO);
        addIngredient("Miniburgers", IngredientPrices.MINIBURGERS);
        addIngredient("Sausage", IngredientPrices.SAUSAGE);
        addIngredient("Sobrassada", IngredientPrices.SOBRASSADA);
        addIngredient("Tuna", IngredientPrices.TUNA);
        addIngredient("Anchovies", IngredientPrices.ANCHOVIES);
        addIngredient("Prawns", IngredientPrices.PRAWNS);
        addIngredient("Onion", IngredientPrices.ONION);
        addIngredient("Bell Pepper", IngredientPrices.BELL_PEPPER);
        addIngredient("Tomato slices", IngredientPrices.TOMATO_SLICES);
        addIngredient("Artichoke", IngredientPrices.ARTICHOKE);
        addIngredient("Mushrooms", IngredientPrices.MUSHROOMS);
        addIngredient("Olives", IngredientPrices.OLIVES);
        addIngredient("Pineapple", IngredientPrices.PINEAPPLE);
        addIngredient("Mozzarella", IngredientPrices.MOZZARELLA);
        addIngredient("Goat Cheese", IngredientPrices.GOAT_CHEESE);
        addIngredient("Brie", IngredientPrices.BRIE);
        addIngredient("Emmental", IngredientPrices.EMMENTAL);
        addIngredient("Roquefort", IngredientPrices.ROQUEFORT);
        addIngredient("Cheddar", IngredientPrices.CHEDDAR);
        addIngredient("BBQ Sauce", IngredientPrices.BBQ_SAUCE);
        addIngredient("Carbonara sauce", IngredientPrices.CARBONARA_SAUCE);
        addIngredient("Honey", IngredientPrices.HONEY);
    }

    private void addIngredient(String name, int priceInCents) {
        ingredients.put(name.toLowerCase(), new Ingredient(name, priceInCents));
    }

    public Ingredient getIngredient(String name) {
        Ingredient ingredient = ingredients.get(name.toLowerCase());
        if (ingredient == null) {
            throw new IllegalArgumentException("Unknown ingredient: " + name);
        }
        return ingredient;
    }

    public Set<String> getAvailableIngredients() {
        return ingredients.keySet();
    }
}