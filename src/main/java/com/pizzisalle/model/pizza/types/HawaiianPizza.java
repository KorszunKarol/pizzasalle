package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.base.Pizza;

public class HawaiianPizza extends Pizza {
    private static final double BASE_PRICE = 9.0;

    public HawaiianPizza() {
        super("Hawaiian", BASE_PRICE, false);
        ingredients.add("Ham");
        ingredients.add("Pineapple");
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}