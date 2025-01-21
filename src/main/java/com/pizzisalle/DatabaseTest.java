package com.pizzisalle;

import com.pizzisalle.constants.Delegations;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.model.order.OrderBuilder;
import com.pizzisalle.model.pizza.types.MargheritaPizza;
import com.pizzisalle.model.pizza.types.HawaiianPizza;
import com.pizzisalle.service.DatabaseManager;
import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
            System.out.println("Starting database tests...\n");

            System.out.println("Test 1: Creating new customer");
            Customer customer = new Customer("John Doe", "123456789", "123 Pizza Street", 25, Delegations.BARCELONA, true);
            DatabaseManager.getInstance().saveCustomer(customer);
            System.out.println("✓ Customer created successfully\n");

            System.out.println("Test 2: Finding customer by phone");
            Customer foundCustomer = DatabaseManager.getInstance().findCustomerByPhone("123456789");
            if (foundCustomer != null) {
                System.out.println("✓ Customer found: " + foundCustomer + "\n");
            } else {
                System.out.println("❌ Customer not found\n");
            }

            System.out.println("Test 3: Creating new order");
            OrderBuilder orderBuilder = new OrderBuilder(foundCustomer);
            orderBuilder.addPizza(new MargheritaPizza());
            orderBuilder.addPizza(new HawaiianPizza());
            Order order = orderBuilder.build();
            DatabaseManager.getInstance().saveOrder(order);
            System.out.println("✓ Order saved successfully\n");


            System.out.println("Test 4: Updating customer's first order status");
            DatabaseManager.getInstance().updateCustomerFirstOrder(customer.getPhone());
            Customer updatedCustomer = DatabaseManager.getInstance().findCustomerByPhone("123456789");
            if (!updatedCustomer.isFirstOrder()) {
                System.out.println("✓ Customer first order status updated successfully\n");
            } else {
                System.out.println("❌ Failed to update customer first order status\n");
            }

            System.out.println("All tests completed successfully!");

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}