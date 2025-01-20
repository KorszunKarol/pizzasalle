package com.pizzisalle.model;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.pizza.base.Pizza;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Customer customer;
    private final List<Pizza> pizzas;
    private final List<Beverage> beverages;
    private CrustType crustType;

    public Order(Customer customer) {
        this.customer = customer;
        this.pizzas = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.crustType = CrustType.ORIGINAL;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(int index) {
        pizzas.remove(index);
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public void setBeverage(Beverage beverage) {
        beverages.clear();
        if (beverage != null) {
            beverages.add(beverage);
        }
    }

    public void setCrust(CrustType type) {
        this.crustType = type;
        pizzas.forEach(pizza -> pizza.setCrustType(type));
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Pizza> getPizzas() {
        return new ArrayList<>(pizzas);
    }

    public List<Beverage> getBeverages() {
        return new ArrayList<>(beverages);
    }

    public CrustType getCrust() {
        return crustType;
    }

    public double getPizzasSubtotal() {
        return pizzas.stream()
                .mapToDouble(Pizza::calculatePrice)
                .sum();
    }

    public double getCrustSubtotal() {
        if (crustType == CrustType.ORIGINAL) return 0.0;
        return (crustType.getExtraCostInCents() / 100.0) * pizzas.size();
    }

    public double getBeveragePrice() {
        return beverages.stream()
                .mapToDouble(Beverage::getPrice)
                .sum();
    }

    public double calculateTotal() {
        double total = getPizzasSubtotal() + getCrustSubtotal() + getBeveragePrice();
        if (customer.isFirstOrder()) {
            total *= 0.9; // 10% discount for first orders
        }
        return total;
    }
}