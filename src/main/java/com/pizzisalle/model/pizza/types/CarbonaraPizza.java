package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class CarbonaraPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public CarbonaraPizza() {
        super("Carbonara", BASE_PRICE, false);
        ingredients.add("Carbonara sauce");
        ingredients.add("Bacon");
        ingredients.add("Onion");
        ingredients.add("Mushrooms");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}