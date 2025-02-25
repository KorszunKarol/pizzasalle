package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class BarcelonaPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    public BarcelonaPizza() {
        super("Barcelona", BASE_PRICE, true);
        ingredients.add("Onion");
        ingredients.add("Beef");
        ingredients.add("Brie");
        ingredients.add("Ham");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}