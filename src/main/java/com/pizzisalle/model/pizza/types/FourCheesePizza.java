package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class FourCheesePizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public FourCheesePizza() {
        super("4 Cheeses", BASE_PRICE, false);
        ingredients.add("Mozzarella");
        ingredients.add("Emmental");
        ingredients.add("Roquefort");
        ingredients.add("Cheddar");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}