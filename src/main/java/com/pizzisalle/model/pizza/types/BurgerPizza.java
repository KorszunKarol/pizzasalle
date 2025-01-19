package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class BurgerPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public BurgerPizza() {
        super("Burger", BASE_PRICE, false);
        ingredients.add("Miniburgers");
        ingredients.add("Egg");
        ingredients.add("Bacon");
        ingredients.add("Onion");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}