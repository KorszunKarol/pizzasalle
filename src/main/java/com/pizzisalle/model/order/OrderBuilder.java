package com.pizzisalle.model.order;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.pizza.base.Pizza;
import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN: Builder - This class implements the Builder pattern to construct Order objects
 * step by step, allowing for flexible order creation while maintaining immutability
 * of the final Order object.
 */
public class OrderBuilder {
    private Order order;

    public OrderBuilder(Customer customer) {
        this.order = new Order(customer);
    }

    public OrderBuilder addPizza(Pizza pizza) {
        order.addPizza(pizza);
        return this;
    }

    public OrderBuilder setCrust(CrustType crust) {
        order.setCrust(crust);
        return this;
    }

    public OrderBuilder setBeverage(Beverage beverage) {
        order.setBeverage(beverage);
        return this;
    }

    public Order build() {
        return order;
    }
}