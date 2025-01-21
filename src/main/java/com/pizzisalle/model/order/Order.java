package com.pizzisalle.model.order;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.pizza.base.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN: This class uses the Builder pattern through OrderBuilder to construct orders
 * in a flexible and readable way.
 */
public class Order {
    private Customer customer;
    private List<Pizza> pizzas;
    private double totalPrice;

    public Order() {
        this.pizzas = new ArrayList<>();
    }

    public Order(Customer customer) {
        this();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = new ArrayList<>(pizzas);
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public void removePizza(int index) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.remove(index);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.calculatePrice();
            total += pizza.getCrustPrice();
            total += pizza.getBeveragePrice();
        }
        this.totalPrice = total;
        return total;
    }

    public double getPizzasSubtotal() {
        return pizzas.stream()
                    .mapToDouble(pizza -> pizza.calculatePrice() + pizza.getCrustPrice())
                    .sum();
    }

    public double getBeveragesSubtotal() {
        return pizzas.stream()
                    .mapToDouble(Pizza::getBeveragePrice)
                    .sum();
    }
}