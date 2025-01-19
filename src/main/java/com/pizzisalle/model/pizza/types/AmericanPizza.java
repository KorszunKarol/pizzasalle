package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.Pizza;
import java.util.HashMap;

public class AmericanPizza extends Pizza {
    private static final double BASE_PRICE = 12.0;

    public AmericanPizza() {
        super("American", BASE_PRICE);
        HashMap<String, Integer> defaultIngredients = new HashMap<>();
        defaultIngredients.put("Frankfurt", 1);
        defaultIngredients.put("Bacon", 1);
        defaultIngredients.put("Egg", 1);
        this.setIngredients(defaultIngredients);
    }

    @Override
    public double calculatePrice() {
        double totalPrice = this.getBasePrice();
        for (var entry : this.getIngredients().entrySet()) {
            switch (entry.getKey()) {
                case "Frankfurt":
                    totalPrice += 1.5 * entry.getValue();
                    break;
                case "Bacon":
                    totalPrice += 1.0 * entry.getValue();
                    break;
                case "Egg":
                    totalPrice += 0.8 * entry.getValue();
                    break;
            }
        }
        return totalPrice;
    }
}