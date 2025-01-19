package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.Pizza;
import java.util.Arrays;

public class HawaiianPizza extends Pizza {
    private static final double BASE_PRICE = 12.0;

    public HawaiianPizza() {
        super("Hawaiian", BASE_PRICE);
        this.ingredients.addAll(Arrays.asList("Ham", "Pineapple"));
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}