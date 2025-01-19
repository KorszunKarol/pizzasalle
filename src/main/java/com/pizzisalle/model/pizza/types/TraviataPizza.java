package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.Pizza;
import java.util.Arrays;

public class TraviataPizza extends Pizza {
    private static final double BASE_PRICE = 12.0;

    public TraviataPizza() {
        super("Traviata", BASE_PRICE);
        this.ingredients.addAll(Arrays.asList("Bacon", "Sausage", "Onion"));
    }

    @Override
    public double calculatePrice() {
        return this.basePrice + (this.ingredients.size() * 1.5);
    }
}