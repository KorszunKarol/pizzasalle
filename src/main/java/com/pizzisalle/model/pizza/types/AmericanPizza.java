package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.utils.IngredientPriceList;

/**
 * Represents an American-style pizza with predefined ingredients.
 * This pizza comes with a standard set of toppings including Frankfurt sausage,
 * bacon, and egg.
 */
public class AmericanPizza extends Pizza {
    private static final double BASE_PRICE = 10.0;

    private static final String FRANKFURT = "Frankfurt";
    private static final String BACON = "Bacon";
    private static final String EGG = "Egg";

    /**
     * Constructs a new American Pizza with default ingredients.
     */
    public AmericanPizza() {
        super("American", BASE_PRICE, false);
        initializeIngredients();
    }

    /**
     * Initializes the standard ingredients for an American Pizza.
     */
    private void initializeIngredients() {
        ingredients.add(FRANKFURT);
        ingredients.add(BACON);
        ingredients.add(EGG);
    }

    /**
     * Calculates the total price of the pizza including base price and ingredients.
     * @return the total price of the pizza
     */
    @Override
    public double calculatePrice() {
        double totalPrice = getBasePrice();
        for (String ingredient : ingredients) {
            totalPrice += IngredientPriceList.getPrice(ingredient);
        }
        return totalPrice;
    }
}