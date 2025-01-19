package com.pizzisalle.model.pizza.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * PATTERN: This class is part of multiple design patterns:
 * 1. Factory Pattern - Serves as the abstract product in the Factory pattern, allowing PizzaFactory
 *    to create concrete pizza instances without exposing instantiation logic.
 * 2. Decorator Pattern - Acts as the Component interface in the Decorator pattern, defining the core
 *    interface that concrete pizzas and decorators must implement. This enables dynamic addition of
 *    ingredients and modifications to existing pizzas.
 * 3. Inheritance - Provides a base template for all pizza types, ensuring consistent structure while
 *    allowing specialization in concrete classes.
 */
public abstract class Pizza {
    protected final String name;
    protected final double basePrice;
    protected final boolean isExclusive;
    protected final List<String> ingredients;

    protected Pizza(String name, double basePrice, boolean isExclusive) {
        this.name = name;
        this.basePrice = basePrice;
        this.isExclusive = isExclusive;
        this.ingredients = new ArrayList<>();
    }

    /**
     * Calculates the total price of the pizza including any modifications from decorators.
     */
    public abstract double calculatePrice();

    public String getName() {
        return name;
    }

    /**
     * Returns the base price of the pizza before any modifications.
     * Used by decorators to calculate the final price.
     */
    public double getBasePrice() {
        return basePrice;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    // Helper method to format price for display
    public String getFormattedPrice() {
        return String.format("€%.2f", calculatePrice());
    }

    // Add methods to get extra ingredients and their quantities
    public List<String> getExtraIngredients() {
        List<String> extras = new ArrayList<>();
        return extras;
    }

    public Map<String, Integer> getIngredientQuantities() {
        return new HashMap<>();  // Base implementation, decorators will override
    }

    public double getExtrasPrice() {
        return 0.0;  // Base implementation, decorators will override
    }
}