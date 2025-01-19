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
        System.out.println("\n--- Order Summary ---");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Delegation: " + order.getCustomer().getDelegation().getName());
        System.out.println("Pizzas:");
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("- " + pizza.getName());
        }
        if (order.getBeverage() != null) {
            System.out.println("Beverage: " + order.getBeverage());
        }
        if (order.getCrust() != null) {
            System.out.println("Crust: " + order.getCrust());
        }
        System.out.println("Total Price: €" + totalPrice);

        while (true) {
            Menu.displayOrderSummary(generateOrderSummary(order, totalPrice));
            int choice = inputHandler.readMenuChoice();

            switch (choice) {
                case 1: // Confirm Order
                    System.out.println("Order confirmed!");
                    return;
                case 2: // Modify Order
                    while (true) {
                        System.out.println("\n--- Modify Order ---");
                        System.out.println("1. Add pizza");
                        System.out.println("2. Remove pizza");
                        System.out.println("3. Change beverage");
                        System.out.println("0. Back to order summary");
                        System.out.print("Enter your choice: ");

                        int modifyChoice = inputHandler.readMenuChoice();
                        switch (modifyChoice) {
                            case 1:
                                Pizza newPizza = inputHandler.readPizzaSelection(order.getCustomer().getDelegation());
                                if (newPizza != null) {
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
                                menu.displayBeverages();
                                Beverage newBeverage = inputHandler.readBeverageChoice(order.getCustomer().getAge());
                                order.setBeverage(newBeverage);
                                totalPrice = order.calculateTotal();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                        if (modifyChoice == 0) break;
                    }
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
        // Other validations
    }

    private String generateOrderSummary(Order order, double totalPrice) {
        StringBuilder summary = new StringBuilder();
        summary.append("Order Summary for ").append(order.getCustomer().getName()).append("\n");
        summary.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        summary.append("📍 Delegation: ").append(order.getCustomer().getDelegation().getName()).append("\n");
        summary.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");

        // Pizzas and their extras
        summary.append("🍕 Ordered Items:\n");
        for (Pizza pizza : order.getPizzas()) {
            summary.append("  • ").append(pizza.getName())
                   .append(" (Base: ").append(pizza.getFormattedPrice()).append(")\n");

            // Show extra ingredients
            List<String> extras = pizza.getExtraIngredients();
            if (!extras.isEmpty()) {
                summary.append("    Extra ingredients:\n");
                for (String extra : extras) {
                    // Get the ingredient from factory to get correct price
                    Ingredient ingredient = IngredientFactory.getInstance().getIngredient(extra);
                    double price = ingredient.getPriceInCents() / 100.0;
                    summary.append("    + ").append(extra)
                           .append(" (+€").append(String.format("%.2f", price)).append(")\n");
                }
            }

            // Show ingredient quantity changes
            Map<String, Integer> quantities = pizza.getIngredientQuantities();
            if (!quantities.isEmpty()) {
                summary.append("    Quantity adjustments:\n");
                for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
                    if (entry.getValue() <= 1) continue; // Skip if quantity is 1 or less
                    Ingredient ingredient = IngredientFactory.getInstance().getIngredient(entry.getKey());
                    double price = (ingredient.getPriceInCents() / 100.0) * (entry.getValue() - 1);
                    summary.append("    × ").append(entry.getKey())
                           .append(" (").append(entry.getValue()).append("×)")
                           .append(" (+€").append(String.format("%.2f", price)).append(")\n");
                }
            }

            // Show total for this pizza with extras
            double pizzaTotal = pizza.calculatePrice();
            double extrasTotal = pizza.getExtrasPrice();
            summary.append("    Base price: €").append(String.format("%.2f", pizzaTotal - extrasTotal)).append("\n");
            if (extrasTotal > 0) {
                summary.append("    Extras total: +€").append(String.format("%.2f", extrasTotal)).append("\n");
            }
            summary.append("    Pizza total: €").append(String.format("%.2f", pizzaTotal)).append("\n\n");
        }
        summary.append("   Pizzas Subtotal: €").append(String.format("%.2f", order.getPizzasSubtotal())).append("\n");

        // Crust cost
        if (order.getCrust() != null && order.getCrust() != CrustType.ORIGINAL) {
            double crustTotal = order.getCrustSubtotal();
            summary.append("\n🥨 Crust Type: ").append(order.getCrust())
                   .append(" (").append(order.getCrust().getFormattedExtraCost())
                   .append(" × ").append(order.getPizzas().size())
                   .append(" pizzas = €").append(String.format("%.2f", crustTotal))
                   .append(")\n");
        }

        // Beverage cost
        if (order.getBeverage() != null) {
            double beveragePrice = order.getBeveragePrice();
            summary.append("\n🥤 Beverage: ").append(order.getBeverage())
                   .append(" (€").append(String.format("%.2f", beveragePrice)).append(")\n");
        }

        // First order discount
        if (order.getCustomer().isFirstOrder()) {
            double subtotal = order.getPizzasSubtotal() + order.getCrustSubtotal() + order.getBeveragePrice();
            double discount = subtotal * 0.1;
            summary.append("\n🎉 First-time customer discount: -€").append(String.format("%.2f", discount)).append("\n");
        }

        // Final total
        summary.append("\n💰 Total: €").append(String.format("%.2f", totalPrice));

        return summary.toString();
    }

    private void saveOrder(Order order) {
        // In a real application, this would save to a database
        System.out.println("Order saved successfully!");
    }
}