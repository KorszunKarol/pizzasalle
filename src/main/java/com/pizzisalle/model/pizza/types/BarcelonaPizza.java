package com.pizzisalle.model.pizza.types;

import com.pizzisalle.model.pizza.Pizza;
import com.pizzisalle.model.pizza.Ingredient;
import java.util.Arrays;

public class BarcelonaPizza extends Pizza {
    
    public BarcelonaPizza() {
        super(
            Arrays.asList(
                new Ingredient("Tomato Sauce", 0.0),
                new Ingredient("Mozzarella", 0.0),
                new Ingredient("Jamón Ibérico", 0.0),
                new Ingredient("Mushrooms", 0.0),
                new Ingredient("Olives", 0.0)
            ),
            12.99
        );
    }

    @Override
    public String getName() {
        return "Barcelona Pizza";
    }
}