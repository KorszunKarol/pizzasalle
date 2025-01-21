package com.pizzisalle.service;

import com.pizzisalle.exception.OrderException;
import com.pizzisalle.model.order.Order;
import com.pizzisalle.service.Menu;
import com.pizzisalle.service.InputHandler;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import com.pizzisalle.service.IngredientFactory;
import com.pizzisalle.model.pizza.decorator.ExtraIngredientDecorator;
import com.pizzisalle.model.pizza.decorator.IngredientQuantityDecorator;
import com.pizzisalle.model.pizza.ingredient.IngredientQuantity;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class OrderProcessor {
    private static final Logger logger = Logger.getLogger("PizziSalle");
    private final InputHandler inputHandler;
    private final Menu menu;

    static {
        try {
            FileHandler fh = new FileHandler("pizzisalle.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OrderProcessor() {
        this.inputHandler = InputHandler.createInputHandler();
        this.menu = inputHandler.getMenu();
    }

    public void processOrder(Order order) {
        while (true) {
            String orderSummary = generateOrderSummary(order);
            Menu.displayOrderSummary(orderSummary);
            int choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1:
                    DatabaseManager.getInstance().saveOrder(order);
                    DatabaseManager.getInstance().updateCustomerFirstOrder(order.getCustomer().getPhone());
                    Menu.displaySuccess("Order confirmed! Thank you for choosing PizziSalle!");
                    return;
                case 2:
                    modifyOrder(order);
                    break;
                case 0:
                    Menu.displaySuccess("Order cancelled.");
                    return;
                default:
                    Menu.displayError("Invalid choice");
            }
        }
    }

    private void modifyOrder(Order order) {
        while (true) {
            displayModifyOrderMenu();
            int choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1:
                    menu.displayPizzaMenu(order.getCustomer().getDelegation());
                    int pizzaChoice = inputHandler.readPizzaSelection(menu.getAvailablePizzas(order.getCustomer().getDelegation()).size());
                    Pizza newPizza = menu.getAvailablePizzas(order.getCustomer().getDelegation()).get(pizzaChoice - 1);
                    newPizza = handlePizzaCustomization(newPizza);
                    menu.displayCrustTypes();
                    int crustChoice = inputHandler.readMenuChoice();
                    newPizza.setCrustType(CrustType.values()[crustChoice - 1]);
                    Beverage beverage = inputHandler.readBeverageChoice(order.getCustomer());
                    if (beverage != null) {
                        newPizza.setBeverage(beverage);
                    }
                    order.addPizza(newPizza);
                    Menu.displaySuccess("Pizza added to order");
                    return;
                case 2:
                    if (order.getPizzas().isEmpty()) {
                        Menu.displayError("No pizzas in order");
                        return;
                    }
                    displayPizzaList(order);
                    int removeChoice = inputHandler.readMenuChoice();
                    if (removeChoice > 0 && removeChoice <= order.getPizzas().size()) {
                        order.removePizza(removeChoice - 1);
                        Menu.displaySuccess("Pizza removed from order");
                    } else {
                        Menu.displayError("Invalid pizza number");
                    }
                    return;
                case 0:
                    return;
                default:
                    Menu.displayError("Invalid choice");
            }
        }
    }

    private void validateOrder(Order order) {
        if (order.getPizzas().size() > 10) {
            throw new OrderException("Maximum 10 pizzas per order");
        }
    }

    private String generateOrderSummary(Order order) {
        StringBuilder summary = new StringBuilder();
        summary.append("Customer: ").append(order.getCustomer().getName()).append("\n");
        summary.append("Delegation: ").append(order.getCustomer().getDelegation().getName()).append("\n\n");

        logger.info("Generating order summary for customer: " + order.getCustomer().getName());

        double totalPrice = 0.0;
        for (Pizza pizza : order.getPizzas()) {
            logger.info("Pizza: " + pizza.getName() + " Base price: €" + pizza.getBasePrice());

            summary.append("Pizza: ").append(pizza.getName()).append("\n");
            summary.append("Base price: ").append(pizza.getFormattedPrice()).append("\n");

            List<String> ingredients = pizza.getIngredients();
            summary.append("Ingredients: ").append(String.join(", ", ingredients)).append("\n");

            if (!pizza.getExtraIngredients().isEmpty()) {
                logger.info("Extra ingredients for " + pizza.getName() + ": " + pizza.getExtraIngredients());
                summary.append("Extra ingredients: ").append(String.join(", ", pizza.getExtraIngredients())).append("\n");
            }

            Map<String, Integer> quantities = pizza.getIngredientQuantities();
            if (!quantities.isEmpty()) {
                logger.info("Ingredient quantities for " + pizza.getName() + ": " + quantities);
                for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
                    summary.append("- ").append(entry.getKey())
                           .append(" x").append(entry.getValue()).append("\n");
                }
            }

            double extrasPrice = pizza.getExtrasPrice();
            logger.info("Extras price for " + pizza.getName() + ": €" + extrasPrice);
            if (extrasPrice > 0) {
                summary.append("Extras price: €").append(String.format("%.2f", extrasPrice)).append("\n");
            }

            if (pizza.getCrustType() != CrustType.ORIGINAL) {
                double crustPrice = pizza.getCrustPrice();
                logger.info("Crust price for " + pizza.getName() + ": €" + crustPrice);
                summary.append("Crust type: ").append(pizza.getCrustType())
                       .append(" (+€").append(String.format("%.2f", crustPrice)).append(")\n");
            }

            if (pizza.getBeverage() != null) {
                double beveragePrice = pizza.getBeveragePrice();
                logger.info("Beverage price for " + pizza.getName() + ": €" + beveragePrice);
                summary.append("Beverage: ").append(pizza.getBeverage())
                       .append(" (€").append(String.format("%.2f", beveragePrice)).append(")\n");
            }

            double pizzaTotal = pizza.calculatePrice() + pizza.getCrustPrice() + pizza.getBeveragePrice();
            logger.info("Total for pizza " + pizza.getName() + ": €" + pizzaTotal);
            summary.append("Pizza total: €").append(String.format("%.2f", pizzaTotal)).append("\n\n");

            totalPrice += pizzaTotal;
        }

        logger.info("Order total price: €" + totalPrice);
        summary.append("Total: €").append(String.format("%.2f", totalPrice));
        return summary.toString();
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

    private void saveOrder(Order order) {
        System.out.println("Order saved successfully!");
    }

    private void displayModifyOrderMenu() {
        System.out.println("\nWhat would you like to modify?");
        System.out.println("1. Add pizza");
        System.out.println("2. Remove pizza");
        System.out.println("0. Back to order summary");
    }

    private void displayPizzaList(Order order) {
        System.out.println("\nCurrent pizzas:");
        for (int i = 0; i < order.getPizzas().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, order.getPizzas().get(i).getName());
        }
    }
}