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

public class Main {
    private final Menu menu;
    private final InputHandler inputHandler;
    private final OrderProcessor orderProcessor;

    public Main() {
        this.menu = new Menu();
        this.inputHandler = new InputHandler();
        this.orderProcessor = new OrderProcessor();
    }

    public void start() {
        while (true) {
            Menu.clearScreen();
            menu.displayMainMenu();
            int choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1:
                    Menu.clearScreen();
                    processNewOrder();
                    break;
                case 0:
                    Menu.clearScreen();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void processNewOrder() {
        Customer customer = inputHandler.readCustomerInfo();
        OrderBuilder orderBuilder = new OrderBuilder(customer);

        Menu.clearScreen();
        System.out.println("\nWelcome to PizziSalle! Your order will be handled by our " +
                          customer.getDelegation().getName() + " delegation.\n");

        int pizzaCount = 0;
        menu.displayPizzaMenu(customer.getDelegation());

        while (pizzaCount < 10) {
            Pizza pizza = inputHandler.readPizzaSelection(customer.getDelegation());
            if (pizza == null) break;

            pizza = handlePizzaCustomization(pizza);

            menu.displayCrustTypes();
            int crustChoice = inputHandler.readInteger("Select crust type: ", 1, 3);
            CrustType selectedCrust = switch (crustChoice) {
                case 1 -> CrustType.ORIGINAL;
                case 2 -> CrustType.THIN;
                case 3 -> CrustType.SICILIAN;
                default -> CrustType.ORIGINAL;
            };
            pizza.setCrustType(selectedCrust);

            menu.displayBeverages();
            Beverage beverage = inputHandler.readBeverageChoice(customer.getAge());
            if (beverage != null) {
                pizza.setBeverage(beverage);
            }

            orderBuilder.addPizza(pizza);
            pizzaCount++;

            if (pizzaCount == 10) {
                System.out.println("Maximum pizza limit reached (10).");
                break;
            }

            System.out.println("\nCurrent order has " + pizzaCount + " pizza(s)");
            if (!readYesNoInput("Would you like to add another pizza?")) break;
            menu.displayPizzaMenu(customer.getDelegation());
        }

        if (pizzaCount == 0) {
            System.out.println("You must add at least one pizza to your order.");
            return;
        }

        Order order = orderBuilder.build();
        orderProcessor.processOrder(order);
    }

    private boolean readYesNoInput(String prompt) {
        while (true) {
            String input = inputHandler.readString(prompt + " (y/n): ").toLowerCase();
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

    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.start();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            app.inputHandler.close();
        }
    }
}