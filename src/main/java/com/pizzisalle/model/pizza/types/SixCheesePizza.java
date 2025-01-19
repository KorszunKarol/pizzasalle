package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class SixCheesePizza extends Pizza {
    private static final double BASE_PRICE = 13.99;

    public SixCheesePizza() {
        super("6 Cheeses", BASE_PRICE, false);
        ingredients.add("Mozzarella");
        ingredients.add("Goat Cheese");
        ingredients.add("Brie");
        ingredients.add("Emmental");
        ingredients.add("Roquefort");
        ingredients.add("Cheddar");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}