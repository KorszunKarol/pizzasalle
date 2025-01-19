package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class MallorcaPizza extends Pizza {
    private static final double BASE_PRICE = 13.99;

    public MallorcaPizza() {
        super("Mallorca", BASE_PRICE, false);
        ingredients.add("Goat Cheese");
        ingredients.add("Emmental");
        ingredients.add("Cheddar");
        ingredients.add("Brie");
        ingredients.add("Sobrassada");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}