package com.pizzisalle.builder;

import com.pizzisalle.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN: Builder
 * This class implements the Builder pattern to construct Order objects step by step.
 * It provides a clear and readable way to create complex Order objects with multiple
 * optional parameters while ensuring all required fields are set before building.
 */
public class OrderBuilder {
    private Customer customer;
    private List<Pizza> pizzas;
    private List<Beverage> beverages;
    private CrustType crustType;
    private double totalAmount;

    public OrderBuilder() {
        this.pizzas = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public OrderBuilder setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
        return this;
    }

    public OrderBuilder addPizza(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza cannot be null");
        }
        this.pizzas.add(pizza);
        this.totalAmount += pizza.getPrice();
        return this;
    }

    public OrderBuilder setCrustType(CrustType crustType) {
        if (crustType == null) {
            throw new IllegalArgumentException("Crust type cannot be null");
        }
        this.crustType = crustType;
        return this;
    }

    public OrderBuilder addBeverage(Beverage beverage) {
        if (beverage == null) {
            throw new IllegalArgumentException("Beverage cannot be null");
        }
        
        // Validate age for alcoholic beverages
        if (beverage.isAlcoholic() && (customer == null || !customer.isAdult())) {
            throw new IllegalStateException("Customer must be an adult to order alcoholic beverages");
        }
        
        this.beverages.add(beverage);
        this.totalAmount += beverage.getPrice();
        return this;
    }

    public Order build() {
        validateOrder();
        
        Order order = new Order();
        order.setCustomer(customer);
        order.setPizzas(new ArrayList<>(pizzas));
        order.setBeverages(new ArrayList<>(beverages));
        order.setCrustType(crustType);
        order.setTotalAmount(totalAmount);
        
        return order;
    }

    private void validateOrder() {
        if (customer == null) {
            throw new IllegalStateException("Order must have a customer");
        }
        if (pizzas.isEmpty()) {
            throw new IllegalStateException("Order must contain at least one pizza");
        }
        if (crustType == null) {
            throw new IllegalStateException("Order must specify a crust type");
        }
    }

    public double getCurrentTotal() {
        return totalAmount;
    }

    public void reset() {
        customer = null;
        pizzas.clear();
        beverages.clear();
        crustType = null;
        totalAmount = 0.0;
    }
}