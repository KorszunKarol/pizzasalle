package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class TarragonaPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    public TarragonaPizza() {
        super("Tarragona", BASE_PRICE, true);
        ingredients.add("Tuna");
        ingredients.add("Prawns");
        ingredients.add("Onion");
        ingredients.add("Ham");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}