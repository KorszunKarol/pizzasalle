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

public class OrderProcessor {
    private final InputHandler inputHandler;
    private final Menu menu;

    public OrderProcessor() {
        this.inputHandler = new InputHandler();
        this.menu = new Menu();
    }

    public void processOrder(Order order) {
        double totalPrice = order.calculateTotal();

        while (true) {
            Menu.displayOrderSummary(generateOrderSummary(order, totalPrice));
            int choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1:
                    System.out.println("Order confirmed!");
                    return;
                case 2:
                    System.out.println("\nWhat would you like to modify?");
                    System.out.println("1. Add pizza");
                    System.out.println("2. Remove pizza");
                    System.out.println("3. Modify pizza");
                    System.out.println("0. Back to order summary");

                    int modifyChoice = inputHandler.readMenuChoice();
                    switch (modifyChoice) {
                        case 1:
                            menu.displayPizzaMenu(order.getCustomer().getDelegation());
                            Pizza newPizza = inputHandler.readPizzaSelection(order.getCustomer().getDelegation());

                            if (newPizza != null) {
                                newPizza = handlePizzaCustomization(newPizza);

                                menu.displayCrustTypes();
                                int crustChoice = inputHandler.readInteger("Select crust type: ", 1, 3);
                                CrustType selectedCrust = switch (crustChoice) {
                                    case 1 -> CrustType.ORIGINAL;
                                    case 2 -> CrustType.THIN;
                                    case 3 -> CrustType.SICILIAN;
                                    default -> CrustType.ORIGINAL;
                                };
                                newPizza.setCrustType(selectedCrust);

                                menu.displayBeverages();
                                Beverage newBeverage = inputHandler.readBeverageChoice(order.getCustomer().getAge());
                                if (newBeverage != null) {
                                    newPizza.setBeverage(newBeverage);
                                }

                                order.addPizza(newPizza);
                                totalPrice = order.calculateTotal();
                            }
                            break;
                        case 2:
                            if (order.getPizzas().size() > 1) {
                                System.out.println("\nCurrent pizzas:");
                                for (int i = 0; i < order.getPizzas().size(); i++) {
                                    System.out.printf("%d. %s\n", i + 1, order.getPizzas().get(i).getName());
                                }
                                int pizzaToRemove = inputHandler.readInteger("Select pizza to remove: ", 1, order.getPizzas().size()) - 1;
                                order.removePizza(pizzaToRemove);
                                totalPrice = order.calculateTotal();
                            } else {
                                System.out.println("Cannot remove pizza - order must contain at least one pizza");
                            }
                            break;
                        case 3:
                            System.out.println("\nSelect pizza to modify:");
                            List<Pizza> pizzas = order.getPizzas();
                            for (int i = 0; i < pizzas.size(); i++) {
                                Pizza p = pizzas.get(i);
                                System.out.printf("%d. %s (Crust: %s, Beverage: %s)\n",
                                    i + 1, p.getName(), p.getCrustType(),
                                    p.getBeverage() != null ? p.getBeverage() : "None");
                            }
                            int pizzaIndex = inputHandler.readInteger("Select pizza: ", 1, pizzas.size()) - 1;
                            Pizza selectedPizza = pizzas.get(pizzaIndex);

                            System.out.println("\nWhat would you like to modify?");
                            System.out.println("1. Customize ingredients");
                            System.out.println("2. Change crust type");
                            System.out.println("3. Change beverage");
                            System.out.println("0. Back");

                            int modifyPizzaChoice = inputHandler.readMenuChoice();
                            switch (modifyPizzaChoice) {
                                case 1:
                                    Pizza modifiedPizza = handlePizzaCustomization(selectedPizza);
                                    pizzas.set(pizzaIndex, modifiedPizza);
                                    break;
                                case 2:
                                    menu.displayCrustTypes();
                                    int newCrustChoice = inputHandler.readInteger("Select crust type: ", 1, 3);
                                    CrustType newCrust = switch (newCrustChoice) {
                                        case 1 -> CrustType.ORIGINAL;
                                        case 2 -> CrustType.THIN;
                                        case 3 -> CrustType.SICILIAN;
                                        default -> CrustType.ORIGINAL;
                                    };
                                    selectedPizza.setCrustType(newCrust);
                                    break;
                                case 3:
                                    menu.displayBeverages();
                                    Beverage newBeverage = inputHandler.readBeverageChoice(order.getCustomer().getAge());
                                    selectedPizza.setBeverage(newBeverage);
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                            totalPrice = order.calculateTotal();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    if (modifyChoice == 0) break;
                    break;
                case 0:
                    System.out.println("Order cancelled");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void validateOrder(Order order) {
        if (order.getPizzas().size() > 10) {
            throw new OrderException("Maximum 10 pizzas per order");
        }
    }

    private String generateOrderSummary(Order order, double totalPrice) {
        StringBuilder summary = new StringBuilder();
        summary.append("Customer: ").append(order.getCustomer().getName()).append("\n");
        summary.append("Delegation: ").append(order.getCustomer().getDelegation().getName()).append("\n\n");

        summary.append("Pizzas:\n");
        for (Pizza pizza : order.getPizzas()) {
            summary.append("- ").append(pizza.getName())
                   .append(" (€").append(String.format("%.2f", pizza.getBasePrice())).append(")\n");

            List<String> additionalIngredients = pizza.getAdditionalIngredients();
            if (additionalIngredients.isEmpty()) {
                summary.append("  Classic pizza with tomato sauce and cheese\n");
            } else {
                summary.append("  Base: Tomato sauce, Cheese\n");
                summary.append("  Additional Ingredients: ").append(String.join(", ", additionalIngredients)).append("\n");
            }

            List<String> extras = pizza.getExtraIngredients();
            if (!extras.isEmpty()) {
                summary.append("  Extra ingredients:\n");
                for (String extra : extras) {
                    Ingredient ingredient = IngredientFactory.getInstance().getIngredient(extra);
                    double price = ingredient.getPriceInCents() / 100.0;
                    summary.append("    + ").append(extra)
                           .append(" (+€").append(String.format("%.2f", price)).append(")\n");
                }
            }

            if (pizza.getCrustType() != CrustType.ORIGINAL) {
                summary.append("  Crust: ").append(pizza.getCrustType())
                       .append(" (+€").append(String.format("%.2f", pizza.getCrustPrice())).append(")\n");
            }

            if (pizza.getBeverage() != null) {
                summary.append("  Beverage: ").append(pizza.getBeverage())
                       .append(" (€").append(String.format("%.2f", pizza.getBeveragePrice())).append(")\n");
            }
            summary.append("\n");
        }

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
}