package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class TexasPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public TexasPizza() {
        super("Texas", BASE_PRICE, false);
        ingredients.add("BBQ Sauce");
        ingredients.add("Onion");
        ingredients.add("Tomato slices");
        ingredients.add("Chicken");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}