package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class MargheritaPizza extends Pizza {
    private static final double BASE_PRICE = 8.00;

    public MargheritaPizza() {
        super("Margherita", BASE_PRICE, false);
        ingredients.add("Tomato sauce");
        ingredients.add("Mozzarella");
        ingredients.add("Basil");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}