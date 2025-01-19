package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class GironaPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    public GironaPizza() {
        super("Girona", BASE_PRICE, true);
        ingredients.add("Beef");
        ingredients.add("Ham");
        ingredients.add("Mushrooms");
        ingredients.add("Chicken");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}