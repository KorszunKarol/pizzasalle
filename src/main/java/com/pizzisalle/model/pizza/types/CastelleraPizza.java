package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class CastelleraPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public CastelleraPizza() {
        super("Castellera", BASE_PRICE, false);
        ingredients.add("Onion");
        ingredients.add("Tuna");
        ingredients.add("Peperoni");
        ingredients.add("Olives");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}