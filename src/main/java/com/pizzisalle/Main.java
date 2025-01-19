package com.pizzisalle;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.types.MargheritaPizza;
import com.pizzisalle.service.DelegationManager;

public class Main {
    public static void main(String[] args) {
        // Create a customer
        Customer customer = new Customer("John Doe", "123456789", "Test Street 1", true, 25);

        // Assign a random delegation
        DelegationManager delegationManager = DelegationManager.getInstance();
        String delegation = delegationManager.assignRandomDelegation();
        customer.setAssignedDelegation(delegation);

        // Create an order
        Order order = new Order(customer);

        // Add a pizza
        Pizza margherita = new MargheritaPizza();
        order.addPizza(margherita);

        // Set crust type and beverage
        order.setCrustType(CrustType.SICILIAN);
        order.setBeverage(Beverage.SODA);

        // Print order details
        System.out.println("\nOrder Details:");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Delegation: " + order.getCustomer().getAssignedDelegation());
        System.out.println("Number of Pizzas: " + order.getPizzas().size());
        System.out.println("Crust Type: " + order.getCrustType());
        System.out.println("Beverage: " + order.getBeverage());
        System.out.println("Total Price: " + order.getTotalPrice());
    }
}