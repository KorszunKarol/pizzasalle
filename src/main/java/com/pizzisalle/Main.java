package com.pizzisalle;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.model.order.OrderBuilder;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.decorator.ExtraIngredientDecorator;
import com.pizzisalle.model.pizza.decorator.IngredientQuantityDecorator;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import com.pizzisalle.service.IngredientFactory;
import com.pizzisalle.service.InputHandler;
import com.pizzisalle.service.Menu;
import com.pizzisalle.service.OrderProcessor;
import com.pizzisalle.model.pizza.ingredient.IngredientQuantity;
import com.pizzisalle.service.DatabaseManager;

public class Main {
    private final Menu menu;
    private final InputHandler inputHandler;
    private final OrderProcessor orderProcessor;

    public Main() {
        this.inputHandler = InputHandler.createInputHandler();
        this.menu = inputHandler.getMenu();
        this.orderProcessor = new OrderProcessor();
    }

    public void start() {
        try {
            System.out.println("\n=== Welcome to PizziSalle ===");
            System.out.println("1. Existing Customer");
            System.out.println("2. New Customer");
            System.out.println("0. Exit");

            int choice = inputHandler.readMenuChoice();
            Customer customer;

            switch (choice) {
                case 1:
                    String phone = inputHandler.readPhoneNumber();
                    customer = DatabaseManager.getInstance().findCustomerByPhone(phone);
                    if (customer == null) {
                        System.out.println("Customer not found. Please register as a new customer.");
                        customer = inputHandler.readCustomerInfo();
                    } else {
                        System.out.println("Welcome back, " + customer.getName() + "!");
                    }
                    break;
                case 2:
                    customer = inputHandler.readCustomerInfo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
                    return;
            }

            System.out.println("You have been assigned to " + customer.getDelegation().getName());
            processOrder(customer);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private boolean readYesNoInput(String prompt) {
        while (true) {
            String input = inputHandler.readString(prompt).toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Please enter 'y' for yes or 'n' for no");
        }
    }

    private Pizza handlePizzaCustomization(Pizza pizza) {
        while (true) {
            try {
                menu.displayCustomizationOptions();
                int choice = inputHandler.readMenuChoice();

                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        Ingredient ingredient = inputHandler.readIngredientChoice();
                        if (ingredient != null) {
                            pizza = new ExtraIngredientDecorator(pizza, ingredient);
                            System.out.println("✓ Added " + ingredient.getName() + " to your pizza");
                        }
                        break;
                    case 2:
                        try {
                            Ingredient ingredientToIncrease = inputHandler.readIngredientChoice();
                            if (ingredientToIncrease != null) {
                                if (!pizza.getIngredients().contains(ingredientToIncrease.getName())) {
                                    System.out.println("❌ This ingredient is not on your pizza. Please add it first.");
                                    continue;
                                }
                                int quantity = inputHandler.readQuantity(ingredientToIncrease.getName());
                                IngredientQuantity ingredientQty = new IngredientQuantity(ingredientToIncrease, quantity);
                                pizza = new IngredientQuantityDecorator(pizza, ingredientQty);
                                System.out.println("✓ Updated " + ingredientToIncrease.getName() + " quantity to " + quantity);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ An error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        return pizza;
    }

    private void processOrder(Customer customer) {
        try {
            OrderBuilder orderBuilder = new OrderBuilder(customer);
            int pizzaCount = 0;

            while (true) {
                if (pizzaCount == 10) {
                    System.out.println("Maximum pizza limit reached (10).");
                    break;
                }

                System.out.println("\nCurrent order has " + pizzaCount + " pizza(s)");
                if (!readYesNoInput("Would you like to add another pizza?")) break;
                menu.displayPizzaMenu(customer.getDelegation());

                int choice = inputHandler.readPizzaSelection(menu.getAvailablePizzas(customer.getDelegation()).size());
                Pizza pizza = menu.getAvailablePizzas(customer.getDelegation()).get(choice - 1);
                pizza = handlePizzaCustomization(pizza);

                menu.displayCrustTypes();
                int crustChoice = inputHandler.readMenuChoice();
                pizza.setCrustType(CrustType.values()[crustChoice - 1]);

                Beverage beverage = inputHandler.readBeverageChoice(customer);
                if (beverage != null) {
                    pizza.setBeverage(beverage);
                }

                orderBuilder.addPizza(pizza);
                pizzaCount++;
            }

            if (pizzaCount == 0) {
                System.out.println("You must add at least one pizza to your order.");
                return;
            }

            Order order = orderBuilder.build();
            orderProcessor.processOrder(order);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main pizziSalle = new Main();
        pizziSalle.start();
    }
}