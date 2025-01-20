package com.pizzisalle.model.pizza.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;

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
    protected CrustType crustType = CrustType.ORIGINAL;
    protected Beverage beverage;

    protected Pizza(String name, double basePrice, boolean isExclusive) {
        this.name = name;
        this.basePrice = basePrice;
        this.isExclusive = isExclusive;
        this.ingredients = new ArrayList<>();

        this.ingredients.add("Tomato sauce");
        this.ingredients.add("Cheese");
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

    /**
     * Returns all ingredients including default ones (tomato sauce and cheese)
     */
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Returns only the additional ingredients (excluding tomato sauce and cheese)
     */
    public List<String> getAdditionalIngredients() {
        List<String> additionalIngredients = new ArrayList<>(ingredients);
        additionalIngredients.remove("Tomato sauce");
        additionalIngredients.remove("Cheese");
        return additionalIngredients;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public String getFormattedPrice() {
        return String.format("€%.2f", calculatePrice());
    }

    public List<String> getExtraIngredients() {
        List<String> extras = new ArrayList<>();
        return extras;
    }

    public Map<String, Integer> getIngredientQuantities() {
        return new HashMap<>();
    }

    public double getExtrasPrice() {
        return 0.0;
    }

    public void setCrustType(CrustType crustType) {
        this.crustType = crustType;
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public double getBeveragePrice() {
        if (beverage == null) return 0.0;
        return switch(beverage) {
            case WATER -> 1.00;
            case SODA -> 1.50;
            case BEER -> 2.00;
        };
    }

    public double getCrustPrice() {
        if (crustType == null || crustType == CrustType.ORIGINAL) return 0.0;
        return crustType.getExtraCostInCents() / 100.0;
    }
}