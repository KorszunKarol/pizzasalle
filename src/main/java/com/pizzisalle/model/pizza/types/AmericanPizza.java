package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.service.IngredientPriceList;

/**
 * Represents an American-style pizza with predefined ingredients.
 * This pizza comes with a standard set of toppings including Frankfurt sausage,
 * bacon, and egg.
 */
public class AmericanPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    /**
     * Constructs a new American Pizza with default ingredients.
     */
    public AmericanPizza() {
        super("American", BASE_PRICE, false);
        ingredients.add("Frankfurt");
        ingredients.add("Bacon");
        ingredients.add("Egg");
    }

    /**
     * Calculates the total price of the pizza including base price and ingredients in cents.
     * @return the total price of the pizza in cents
     */
    @Override
    public double calculatePrice() {
        double totalPrice = getBasePrice();
        for (String ingredient : ingredients) {
            totalPrice += IngredientPriceList.getPrice(ingredient) / 100.0;
        }
        return totalPrice;
    }
}