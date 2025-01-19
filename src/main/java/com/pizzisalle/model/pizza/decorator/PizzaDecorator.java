package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * PATTERN: Decorator - This abstract class serves as the base decorator for all pizza modifications.
 * It implements the same interface as the component (Pizza) and has a reference to an instance
 * of the component. This allows for dynamic addition of new behaviors to pizzas at runtime.
 */
public abstract class PizzaDecorator extends Pizza {
    protected final Pizza pizza;

    protected PizzaDecorator(Pizza pizza) {
        super(pizza.getName(), pizza.getBasePrice(), pizza.isExclusive());
        this.pizza = pizza;
        this.ingredients.addAll(pizza.getIngredients());
    }

    @Override
    public double calculatePrice() {
        return pizza.calculatePrice();
    }

    @Override
    public List<String> getIngredients() {
        return new ArrayList<>(pizza.getIngredients());
    }

    @Override
    public List<String> getExtraIngredients() {
        return pizza.getExtraIngredients();
    }

    @Override
    public Map<String, Integer> getIngredientQuantities() {
        return pizza.getIngredientQuantities();
    }

    @Override
    public double getExtrasPrice() {
        return pizza.getExtrasPrice();
    }
}