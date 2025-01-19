package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class SpanishPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public SpanishPizza() {
        super("Spanish", BASE_PRICE, false);
        ingredients.add("Jamón Serrano");
        ingredients.add("Brie");
        ingredients.add("Olives");
        ingredients.add("Mushrooms");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}