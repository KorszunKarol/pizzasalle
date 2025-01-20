package com.pizzisalle.service;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.constants.Delegations;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String SEPARATOR = "════════════════════════════════════════════════════";
    private static final String SMALL_SEPARATOR = "────────────────────────────────────";

    public void displayMainMenu() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("             🍕 PizziSalle Menu 🍕");
        System.out.println(SEPARATOR);
        System.out.println("1. New Order");
        System.out.println("0. Exit");
        System.out.println(SMALL_SEPARATOR);
        System.out.print("Enter your choice: ");
    }

    public void displayPizzaMenu(Delegations delegation) {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("             🍕 Pizza Menu - " + delegation.getName() + " 🍕");
        System.out.println(SEPARATOR);

        List<Pizza> availablePizzas = getAvailablePizzas(delegation);
        for (int i = 0; i < availablePizzas.size(); i++) {
            Pizza pizza = availablePizzas.get(i);
            String exclusive = pizza.isExclusive() ? " ⭐" : "";

            String ingredientsList = pizza.getIngredients().isEmpty() ?
                "Classic (Tomato sauce & Cheese)" :
                String.join(", ", pizza.getIngredients());

            System.out.printf("%2d. %-20s €%-8.2f%n",
                (i + 1),
                pizza.getName(),
                pizza.getBasePrice());
            System.out.printf("     └─ Ingredients: %s%s%n", ingredientsList, exclusive);
            System.out.println();
        }

        System.out.println(SEPARATOR);
        System.out.println("0. Done adding pizzas");
        System.out.print("Enter your choice: ");
    }

    private List<Pizza> getAvailablePizzas(Delegations delegation) {
        List<Pizza> availablePizzas = new ArrayList<>();
        availablePizzas.add(new MargheritaPizza());
        availablePizzas.add(new HawaiianPizza());
        availablePizzas.add(new BaconCrispyPizza());
        availablePizzas.add(new AmericanPizza());
        availablePizzas.add(new TraviataPizza());
        availablePizzas.add(new BurgerPizza());
        availablePizzas.add(new CastelleraPizza());
        availablePizzas.add(new CowboyPizza());
        availablePizzas.add(new TexasPizza());
        availablePizzas.add(new CoastPizza());
        availablePizzas.add(new BBQPizza());
        availablePizzas.add(new DiabloPizza());
        availablePizzas.add(new CarbonaraPizza());
        availablePizzas.add(new SpanishPizza());
        availablePizzas.add(new FourCheesePizza());
        availablePizzas.add(new PepperoniPizza());
        availablePizzas.add(new VegetalPizza());
        availablePizzas.add(new SixCheesePizza());
        availablePizzas.add(new MallorcaPizza());
        availablePizzas.add(new CarbonaraDeluxePizza());

        switch (delegation) {
            case BARCELONA:
                availablePizzas.add(new BarcelonaPizza());
                break;
            case GIRONA:
                availablePizzas.add(new GironaPizza());
                break;
            case TARRAGONA:
                availablePizzas.add(new TarragonaPizza());
                break;
            case LLEIDA:
                availablePizzas.add(new LleidaPizza());
                break;
        }
        return availablePizzas;
    }

    public void displayBeverages() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("             🥤 Beverages 🥤");
        System.out.println(SEPARATOR);
        System.out.println("1. Water     €1.00");
        System.out.println("2. Soda      €1.50");
        System.out.println("3. Beer      €2.00 (18+ only)");
        System.out.println("0. No beverage");
        System.out.println(SMALL_SEPARATOR);
        System.out.print("Enter your choice: ");
    }

    public void displayCrustTypes() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("             🥨 Crust Types 🥨");
        System.out.println(SEPARATOR);
        System.out.println("1. Original");
        System.out.println("2. Thin");
        System.out.println("3. Sicilian");
        System.out.println(SMALL_SEPARATOR);
        System.out.print("Enter your choice: ");
    }

    public void displayCustomizationOptions() {
        System.out.println(SEPARATOR);
        System.out.println("         🛠️  Pizza Customization 🛠️ ");
        System.out.println(SEPARATOR);
        System.out.println("1. Add extra ingredient");
        System.out.println("2. Increase ingredient quantity");
        System.out.println("0. Done customizing pizza");
        System.out.println(SMALL_SEPARATOR);
        System.out.print("Enter your choice: ");
    }

    public static void displayExtraIngredients() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("            🧀 Extra Ingredients 🧀");
        System.out.println(SEPARATOR);
        System.out.println("Maximum 10 extra ingredients per pizza\n");
        System.out.println("1. Extra Cheese      €1.00");
        System.out.println("2. Mushrooms         €0.50");
        System.out.println("3. Pepperoni         €0.75");
        System.out.println("4. Ham               €0.75");
        System.out.println("5. Pineapple         €0.50");
        System.out.println("6. Chicken           €1.00");
        System.out.println("7. Mixed Vegetables  €0.75");
        System.out.println("0. Done adding extras");
        System.out.println(SEPARATOR);
        System.out.print("Select extra ingredient: ");
    }

    public static void displayOrderSummary(String orderDetails) {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("            📝 Order Summary 📝");
        System.out.println(SEPARATOR);
        System.out.println(orderDetails);
        System.out.println(SEPARATOR);
        System.out.println("1. Confirm Order");
        System.out.println("2. Modify Order");
        System.out.println("0. Cancel Order");
        System.out.println(SEPARATOR);
        System.out.print("Select an option: ");
    }

    public static void displayError(String message) {
        System.out.println("\n❌ ERROR: " + message);
        System.out.println("Press Enter to continue...");
    }

    public static void displaySuccess(String message) {
        System.out.println("\n✅ SUCCESS: " + message);
        System.out.println("Press Enter to continue...");
    }

    public static boolean isPizzaAvailable(String pizzaName) {
        return false;
    }

    public static void clearScreen() {
        try {
            ProcessBuilder pb = new ProcessBuilder("clear");
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}