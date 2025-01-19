package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class BaconCrispyPizza extends Pizza {
    private static final double BASE_PRICE = 10.0;

    public BaconCrispyPizza() {
        super("Bacon Crispy", BASE_PRICE, false);
        ingredients.add("Ham");
        ingredients.add("Chicken");
        ingredients.add("Bacon");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}