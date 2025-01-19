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
        // Basic ingredients
        addIngredient("Tomato sauce", 50);  // €0.50
        addIngredient("Cheese", 50);        // €0.50

        // Meats
        addIngredient("Ham", 100);          // €1.00
        addIngredient("Chicken", 100);      // €1.00
        addIngredient("Bacon", 100);        // €1.00
        addIngredient("Beef", 120);         // €1.20
        addIngredient("Frankfurt", 100);    // €1.00
        addIngredient("Pepperoni", 100);    // €1.00
        addIngredient("Jamón Serrano", 200);// €2.00
        addIngredient("Miniburgers", 150);  // €1.50
        addIngredient("Sausage", 100);      // €1.00
        addIngredient("Sobrassada", 150);   // €1.50

        // Seafood
        addIngredient("Tuna", 100);         // €1.00
        addIngredient("Anchovies", 100);    // €1.00
        addIngredient("Prawns", 150);       // €1.50

        // Vegetables
        addIngredient("Onion", 50);         // €0.50
        addIngredient("Bell Pepper", 50);   // €0.50
        addIngredient("Tomato slices", 50); // €0.50
        addIngredient("Artichoke", 100);    // €1.00
        addIngredient("Mushrooms", 80);     // €0.80
        addIngredient("Olives", 50);        // €0.50
        addIngredient("Pineapple", 80);     // €0.80

        // Cheeses
        addIngredient("Mozzarella", 100);   // €1.00
        addIngredient("Goat Cheese", 150);  // €1.50
        addIngredient("Brie", 150);         // €1.50
        addIngredient("Emmental", 120);     // €1.20
        addIngredient("Roquefort", 150);    // €1.50
        addIngredient("Cheddar", 120);      // €1.20

        // Sauces and Others
        addIngredient("BBQ Sauce", 80);     // €0.80
        addIngredient("Carbonara sauce", 100);// €1.00
        addIngredient("Honey", 50);         // €0.50
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