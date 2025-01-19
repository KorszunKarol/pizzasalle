package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class HawaiianPizza extends Pizza {
    private static final double BASE_PRICE = 10.99;

    public HawaiianPizza() {
        super("Hawaiian", BASE_PRICE, false);
        ingredients.add("Pineapple");
        ingredients.add("Ham");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}