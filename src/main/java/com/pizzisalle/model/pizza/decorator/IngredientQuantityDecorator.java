package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.ingredient.IngredientQuantity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * PATTERN: Decorator
 * This class implements the Decorator pattern to modify the quantity of existing ingredients.
 * It allows increasing the amount of an ingredient that's already on the pizza.
 */
public class IngredientQuantityDecorator extends PizzaDecorator {
    private final IngredientQuantity ingredientQuantity;

    public IngredientQuantityDecorator(Pizza pizza, IngredientQuantity ingredientQuantity) {
        super(pizza);
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public double calculatePrice() {
        double basePrice = super.calculatePrice();
        double additionalQuantityPrice = (ingredientQuantity.getQuantity() - 1) *
                                       (ingredientQuantity.getIngredient().getPriceInCents() / 100.0);
        return basePrice + additionalQuantityPrice;
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

    @Override
    public List<String> getExtraIngredients() {
        List<String> extras = new ArrayList<>(super.getExtraIngredients());
        String ingredientName = ingredientQuantity.getIngredient().getName();
        if (ingredientQuantity.getQuantity() > 1) {
            extras.add(ingredientName + " (x" + ingredientQuantity.getQuantity() + ")");
        }
        return extras;
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
        return super.getExtrasPrice() +
               ((ingredientQuantity.getQuantity() - 1) * (ingredientQuantity.getIngredient().getPriceInCents() / 100.0));
    }
}