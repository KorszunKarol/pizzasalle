package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.base.Pizza;
import java.util.List;

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
    public String getName() {
        return pizza.getName();
    }

    @Override
    public List<String> getIngredients() {
        return pizza.getIngredients();
    }

    @Override
    public double getBasePrice() {
        return pizza.getBasePrice();
    }

    @Override
    public boolean isExclusive() {
        return pizza.isExclusive();
    }
}