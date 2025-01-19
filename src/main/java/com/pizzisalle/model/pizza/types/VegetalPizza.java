package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class VegetalPizza extends Pizza {
    private static final double BASE_PRICE = 12.99;

    public VegetalPizza() {
        super("Vegetal", BASE_PRICE, false);
        ingredients.add("Onion");
        ingredients.add("Bell Pepper");
        ingredients.add("Tomato slices");
        ingredients.add("Artichoke");
        ingredients.add("Mushrooms");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}