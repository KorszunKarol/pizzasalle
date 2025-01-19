package com.pizzisalle.builder;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.model.pizza.base.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN: Builder - This class implements the Builder pattern to construct Order objects
 * step by step, ensuring all required validations are performed during construction.
 */
public class OrderBuilder {
    private Customer customer;
    private List<Pizza> pizzas;
    private List<Beverage> beverages;
    private CrustType crustType;

    public OrderBuilder() {
        pizzas = new ArrayList<>();
        beverages = new ArrayList<>();
        crustType = CrustType.ORIGINAL;
    }

    public OrderBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public OrderBuilder addPizza(Pizza pizza) {
        if (pizza != null) {
            pizzas.add(pizza);
        }
        return this;
    }

    public OrderBuilder setCrustType(CrustType crustType) {
        if (crustType != null) {
            this.crustType = crustType;
        }
        return this;
    }

    public OrderBuilder addBeverage(Beverage beverage) {
        if (beverage != null) {
            beverages.add(beverage);
        }
        return this;
    }

    public Order build() {
        if (customer == null) {
            throw new IllegalStateException("Customer is required");
        }

        Order order = new Order(customer);
        pizzas.forEach(order::addPizza);
        order.setCrustType(crustType);
        beverages.forEach(order::addBeverage);

        return order;
    }
}