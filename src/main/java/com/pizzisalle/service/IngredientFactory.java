package com.pizzisalle.service;

import com.pizzisalle.model.pizza.ingredient.Ingredient;
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

        addIngredient("Tomato sauce", 50);
        addIngredient("Cheese", 50);

        addIngredient("Ham", 100);
        addIngredient("Chicken", 100);
        addIngredient("Bacon", 100);
        addIngredient("Beef", 120);
        addIngredient("Frankfurt", 100);
        addIngredient("Pepperoni", 100);
        addIngredient("Jamón Serrano", 200);
        addIngredient("Miniburgers", 150);
        addIngredient("Sausage", 100);
        addIngredient("Sobrassada", 150);


        addIngredient("Tuna", 100);
        addIngredient("Anchovies", 100);
        addIngredient("Prawns", 150);

        addIngredient("Onion", 50);
        addIngredient("Bell Pepper", 50);
        addIngredient("Tomato slices", 50);
        addIngredient("Artichoke", 100);
        addIngredient("Mushrooms", 80);
        addIngredient("Olives", 50);
        addIngredient("Pineapple", 80);

        addIngredient("Mozzarella", 100);
        addIngredient("Goat Cheese", 150);
        addIngredient("Brie", 150);
        addIngredient("Emmental", 120);
        addIngredient("Roquefort", 150);
        addIngredient("Cheddar", 120);


        addIngredient("BBQ Sauce", 80);
        addIngredient("Carbonara sauce", 100);
        addIngredient("Honey", 50);
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