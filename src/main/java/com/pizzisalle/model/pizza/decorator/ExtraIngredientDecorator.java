package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import java.util.List;
import java.util.ArrayList;

/**
 * PATTERN: Decorator
 * This class implements the Decorator pattern to dynamically add new ingredients to a pizza.
 * It allows adding extra ingredients without altering the original pizza structure.
 */
public class ExtraIngredientDecorator extends PizzaDecorator {
    private final Ingredient extraIngredient;

    public ExtraIngredientDecorator(Pizza pizza, Ingredient extraIngredient) {
        super(pizza);
        this.extraIngredient = extraIngredient;
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() + (extraIngredient.getPriceInCents() / 100.0);
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>(super.getIngredients());
        ingredients.add(extraIngredient.getName());
        return ingredients;
    }

    @Override
    public String toString() {
        return super.toString() + " + " + extraIngredient.getName();
    }

    public Ingredient getExtraIngredient() {
        return extraIngredient;
    }

    @Override
    public List<String> getExtraIngredients() {
        List<String> extras = new ArrayList<>(super.getExtraIngredients());
        extras.add(extraIngredient.getName());
        return extras;
    }

    @Override
    public double getExtrasPrice() {
        return super.getExtrasPrice() + (extraIngredient.getPriceInCents() / 100.0);
    }
}