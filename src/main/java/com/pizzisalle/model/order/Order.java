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
    private CrustType crust;
    private Beverage beverage;
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
        this.pizzas = pizzas;
    }

    public CrustType getCrust() {
        return crust;
    }

    public void setCrust(CrustType crust) {
        this.crust = crust;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

        // Calculate pizzas base price
        for (Pizza pizza : pizzas) {
            total += pizza.calculatePrice();
        }

        // Add crust price for each pizza if not original
        if (crust != null && crust != CrustType.ORIGINAL) {
            total += (crust.getExtraCostInCents() / 100.0) * pizzas.size();
        }

        // Add beverage price
        if (beverage != null) {
            total += switch(beverage) {
                case WATER -> 1.00;
                case SODA -> 1.50;
                case BEER -> 2.00;
            };
        }

        // Apply first-order discount if applicable
        if (customer.isFirstOrder()) {
            total = total * 0.9; // 10% discount
        }

        this.totalPrice = total;
        return total;
    }

    public double getPizzasSubtotal() {
        return pizzas.stream()
                    .mapToDouble(Pizza::calculatePrice)
                    .sum();
    }

    public double getCrustSubtotal() {
        if (crust == null || crust == CrustType.ORIGINAL) return 0.0;
        return (crust.getExtraCostInCents() / 100.0) * pizzas.size();
    }

    public double getBeveragePrice() {
        if (beverage == null) return 0.0;
        return switch(beverage) {
            case WATER -> 1.00;
            case SODA -> 1.50;
            case BEER -> 2.00;
        };
    }
}