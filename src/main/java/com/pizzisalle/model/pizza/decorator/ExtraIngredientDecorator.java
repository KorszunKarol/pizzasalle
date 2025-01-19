package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import java.util.List;

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
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add(extraIngredient.getName() + " (x" + quantity + ")");
        return ingredients;
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() + (extraIngredient.getPrice() * quantity * EXTRA_INGREDIENT_PRICE_MULTIPLIER);
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