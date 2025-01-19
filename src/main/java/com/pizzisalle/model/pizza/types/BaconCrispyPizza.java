package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.Pizza;
import java.util.Arrays;

public class BaconCrispyPizza extends Pizza {
    private static final double BASE_PRICE = 13.99;

    public BaconCrispyPizza() {
        super("Bacon Crispy");
        this.ingredients.addAll(Arrays.asList("Ham", "Chicken", "Bacon"));
    }

    @Override
    public double calculatePrice() {
        return BASE_PRICE + calculateIngredientsPrice();
    }
}