package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class DiabloPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public DiabloPizza() {
        super("Diablo", BASE_PRICE, false);
        ingredients.add("Ham");
        ingredients.add("Beef");
        ingredients.add("Bacon");
        ingredients.add("Chicken");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}