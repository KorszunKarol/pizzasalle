package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class BBQPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public BBQPizza() {
        super("BBQ", BASE_PRICE, false);
        ingredients.add("BBQ Sauce");
        ingredients.add("Beef");
        ingredients.add("Bacon");
        ingredients.add("Chicken");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}