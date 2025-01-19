package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class CarbonaraDeluxePizza extends Pizza {
    private static final double BASE_PRICE = 13.99;

    public CarbonaraDeluxePizza() {
        super("Carbonara Deluxe", BASE_PRICE, false);
        ingredients.add("Carbonara sauce");
        ingredients.add("Bacon");
        ingredients.add("Onion");
        ingredients.add("Mushrooms");
        ingredients.add("Goat Cheese");
        ingredients.add("Honey");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}