package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.Pizza;
import com.pizzisalle.model.pizza.ingredient.Ingredient;

import java.util.HashMap;
import java.util.Map;

/**
 * PATTERN: Decorator
 * This class implements the Decorator pattern to dynamically add or modify ingredients in a pizza.
 * It allows adding new ingredients or increasing the quantity of existing ones without altering
 * the original pizza structure.
 */
public class ExtraIngredientDecorator extends PizzaDecorator {
    private final Ingredient extraIngredient;
    private final int quantity;
    private static final double EXTRA_INGREDIENT_PRICE_MULTIPLIER = 0.5;

    public ExtraIngredientDecorator(Pizza pizza, Ingredient ingredient, int quantity) {
        super(pizza);
        this.extraIngredient = ingredient;
        this.quantity = quantity;
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {
        Map<Ingredient, Integer> ingredients = new HashMap<>(super.getIngredients());
        ingredients.merge(extraIngredient, quantity, Integer::sum);
        return ingredients;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + (extraIngredient.getPrice() * quantity * EXTRA_INGREDIENT_PRICE_MULTIPLIER);
    }

    @Override
    public String toString() {
        return super.toString() + " + " + quantity + "x " + extraIngredient.getName();
    }

    public Ingredient getExtraIngredient() {
        return extraIngredient;
    }

    public int getQuantity() {
        return quantity;
    }
}