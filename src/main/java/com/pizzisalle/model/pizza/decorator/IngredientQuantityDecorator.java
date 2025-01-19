package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.ingredient.IngredientQuantity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * PATTERN: Decorator
 * This class implements the Decorator pattern to modify the quantity of existing ingredients.
 * It allows increasing the amount of an ingredient that's already on the pizza.
 */
public class IngredientQuantityDecorator extends PizzaDecorator {
    private static final double QUANTITY_PRICE_MULTIPLIER = 0.3;
    private final IngredientQuantity ingredientQuantity;

    public IngredientQuantityDecorator(Pizza pizza, IngredientQuantity ingredientQuantity) {
        super(pizza);
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public double calculatePrice() {
        return super.calculatePrice() +
               (ingredientQuantity.getTotalPriceInCents() / 100.0 * QUANTITY_PRICE_MULTIPLIER);
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        String ingredientName = ingredientQuantity.getIngredient().getName();
        if (ingredientQuantity.getQuantity() > 1) {
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).equals(ingredientName)) {
                    ingredients.set(i, ingredientName + " (x" + ingredientQuantity.getQuantity() + ")");
                    break;
                }
            }
        }
        return ingredients;
    }

    public IngredientQuantity getIngredientQuantity() {
        return ingredientQuantity;
    }

    @Override
    public Map<String, Integer> getIngredientQuantities() {
        Map<String, Integer> quantities = new HashMap<>(pizza.getIngredientQuantities());
        quantities.put(ingredientQuantity.getIngredient().getName(), ingredientQuantity.getQuantity());
        return quantities;
    }

    @Override
    public double getExtrasPrice() {
        return pizza.getExtrasPrice() +
               (ingredientQuantity.getIngredient().getPriceInCents() / 100.0) * (ingredientQuantity.getQuantity() - 1);
    }
}