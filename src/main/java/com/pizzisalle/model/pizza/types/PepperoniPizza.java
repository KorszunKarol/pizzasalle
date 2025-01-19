package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class PepperoniPizza extends Pizza {
    private static final double BASE_PRICE = 11.99;

    public PepperoniPizza() {
        super("Pepperoni", BASE_PRICE, false);
        ingredients.add("Pepperoni");
        ingredients.add("Ham");
        ingredients.add("Beef");
        ingredients.add("Bacon");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}