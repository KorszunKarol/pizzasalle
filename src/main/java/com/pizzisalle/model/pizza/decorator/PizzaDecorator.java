package com.pizzisalle.model.pizza.decorator;

import com.pizzisalle.model.pizza.Pizza;
import java.util.List;

/**
 * PATTERN: Decorator
 * Abstract base class for all pizza decorators that add extra ingredients or modify existing ones.
 * This class implements the Decorator pattern by wrapping a Pizza object and delegating all method
 * calls to it while allowing subclasses to modify the behavior.
 */
public abstract class PizzaDecorator extends Pizza {
    
    protected final Pizza pizza;

    /**
     * Constructor for PizzaDecorator
     * @param pizza The pizza to be decorated
     */
    protected PizzaDecorator(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza cannot be null");
        }
        this.pizza = pizza;
    }

    @Override
    public String getName() {
        return pizza.getName();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }

    @Override
    public List<String> getIngredients() {
        return pizza.getIngredients();
    }

    @Override
    public boolean isExclusive() {
        return pizza.isExclusive();
    }

    @Override
    public String getDelegation() {
        return pizza.getDelegation();
    }
}