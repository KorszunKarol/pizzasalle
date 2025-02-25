package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class TraviataPizza extends Pizza {
    private static final double BASE_PRICE = 10.00;

    public TraviataPizza() {
        super("Traviata", BASE_PRICE, false);
        ingredients.add("Bacon");
        ingredients.add("Sausage");
        ingredients.add("Onion");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}