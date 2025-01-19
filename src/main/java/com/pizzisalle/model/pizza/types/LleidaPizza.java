package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class LleidaPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    public LleidaPizza() {
        super("Lleida", BASE_PRICE, true);
        ingredients.add("BBQ Sauce");
        ingredients.add("Beef");
        ingredients.add("Chicken");
        ingredients.add("Mushrooms");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}