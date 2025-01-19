package com.pizzisalle.model.order;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.exception.OrderException;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.pizza.base.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN: This class uses the Builder pattern through OrderBuilder to construct orders
 * in a flexible and readable way.
 */
public class Order {
    private final Customer customer;
    private final List<Pizza> pizzas;
    private CrustType crustType;
    private List<Beverage> beverages;
    private double totalPrice;

    public Order(Customer customer) {
        if (customer == null) throw new OrderException("Customer cannot be null");
        this.customer = customer;
        this.pizzas = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.crustType = CrustType.ORIGINAL; // Default crust type
    }

    public void addPizza(Pizza pizza) {
        if (pizza == null) {
            throw new OrderException("Pizza cannot be null");
        }
        if (pizzas.size() >= 10) {
            throw new OrderException("Cannot add more than 10 pizzas to an order");
        }
        pizzas.add(pizza);
    }

    public void setCrustType(CrustType crustType) {
        if (crustType == null) throw new OrderException("Crust type cannot be null");
        this.crustType = crustType;
    }

    public void addBeverage(Beverage beverage) {
        if (beverage == null) {
            throw new OrderException("Beverage cannot be null");
        }
        if (beverages.size() >= 10) {
            throw new OrderException("Cannot add more than 10 beverages to an order");
        }
        if (beverage == Beverage.BEER && !customer.isAdult()) {
            throw new OrderException("Customer must be 18 or older to order beer");
        }
        beverages.add(beverage);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Pizza> getPizzas() {
        return new ArrayList<>(pizzas);
    }

    public CrustType getCrustType() {
        return crustType;
    }

    public List<Beverage> getBeverages() {
        return new ArrayList<>(beverages);
    }

    public double calculateTotalPrice() {
        try {
            double total = 0;

            // Calculate pizza prices
            for (Pizza pizza : pizzas) {
                total += pizza.calculatePrice();
                // Add crust type extra cost for each pizza
                total += crustType.getExtraCost();
            }

            // Add beverage prices
            for (Beverage beverage : beverages) {
                total += beverage.getPrice();
            }

            this.totalPrice = total;
            return total;
        } catch (Exception e) {
            throw new OrderException("Error calculating total price: " + e.getMessage());
        }
    }
}