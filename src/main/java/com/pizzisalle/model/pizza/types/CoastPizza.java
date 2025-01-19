package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class CoastPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public CoastPizza() {
        super("Coast", BASE_PRICE, false);
        ingredients.add("Tuna");
        ingredients.add("Anchovies");
        ingredients.add("Prawns");
        ingredients.add("Pineapple");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}