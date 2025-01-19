package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class CowboyPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public CowboyPizza() {
        super("Cowboy", BASE_PRICE, false);
        ingredients.add("BBQ Sauce");
        ingredients.add("Cheddar");
        ingredients.add("Roquefort");
        ingredients.add("Bacon");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}