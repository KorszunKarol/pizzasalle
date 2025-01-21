package com.pizzisalle.service;

import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.CrustType;
import com.pizzisalle.constants.Delegations;
import com.pizzisalle.model.customer.Customer;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String SEPARATOR = "===========================================";
    private static final String SMALL_SEPARATOR = "-------------------------------------------";
    private InputHandler inputHandler;

    public Menu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

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
        System.out.println("             🍕 Pizza Menu 🍕");
        System.out.println(SEPARATOR);
        System.out.println("All pizzas come with tomato sauce and cheese by default.\n");

        List<Pizza> pizzas = getAvailablePizzas(delegation);
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            String exclusive = pizza.isExclusive() ? " ⭐" : "";

            System.out.printf("%2d. %-20s €%-8.2f%n",
                (i + 1),
                pizza.getName(),
                pizza.getBasePrice());

            List<String> additionalIngredients = pizza.getAdditionalIngredients();
            String ingredients = additionalIngredients.isEmpty() ?
                "No additional ingredients" :
                String.join(", ", additionalIngredients);

            System.out.printf("     └─ %s%s%n%n", ingredients, exclusive);
        }
        System.out.println(SMALL_SEPARATOR);
        System.out.print("Select a pizza (enter number): ");
    }

    public List<Pizza> getAvailablePizzas(Delegations delegation) {
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
            case BARCELONA -> availablePizzas.add(new BarcelonaPizza());
            case GIRONA -> availablePizzas.add(new GironaPizza());
            case TARRAGONA -> availablePizzas.add(new TarragonaPizza());
            case LLEIDA -> availablePizzas.add(new LleidaPizza());
        }
        return availablePizzas;
    }

    public Beverage displayBeverages(Customer customer) {
        System.out.println("\nAvailable beverages:");
        System.out.println("1. Water");
        System.out.println("2. Soda");
        if (customer.isLegalForBeer()) {
            System.out.println("3. Beer");
        }
        System.out.println("0. No beverage");

        while (true) {
            int choice = inputHandler.readMenuChoice();
            switch (choice) {
                case 0:
                    return null;
                case 1:
                    return Beverage.WATER;
                case 2:
                    return Beverage.SODA;
                case 3:
                    if (customer.isLegalForBeer()) {
                        return Beverage.BEER;
                    }
                    System.out.println("You must be 18 or older to order beer");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
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

        System.out.println("1. Cheese              €1.00");
        System.out.println("2. Ham                 €1.00");
        System.out.println("3. Chicken             €1.00");
        System.out.println("4. Bacon               €1.00");
        System.out.println("5. Beef                €1.20");
        System.out.println("6. Frankfurt           €1.00");
        System.out.println("7. Pepperoni           €1.00");
        System.out.println("8. Jamón Serrano       €2.00");
        System.out.println("9. Miniburgers         €1.50");
        System.out.println("10. Sausage            €1.00");
        System.out.println("11. Sobrassada         €1.50");
        System.out.println("12. Tuna               €1.00");
        System.out.println("13. Anchovies          €1.00");
        System.out.println("14. Prawns             €1.50");
        System.out.println("15. Onion              €0.50");
        System.out.println("16. Bell Pepper        €0.50");
        System.out.println("17. Tomato slices      €0.50");
        System.out.println("18. Artichoke          €1.00");
        System.out.println("19. Mushrooms          €0.80");
        System.out.println("20. Olives             €0.50");
        System.out.println("21. Pineapple          €0.80");
        System.out.println("22. Mozzarella         €1.00");
        System.out.println("23. Goat Cheese        €1.50");
        System.out.println("24. Brie               €1.50");
        System.out.println("25. Emmental           €1.20");
        System.out.println("26. Roquefort          €1.50");
        System.out.println("27. Cheddar            €1.20");
        System.out.println("28. BBQ Sauce          €0.80");
        System.out.println("29. Carbonara sauce    €1.00");
        System.out.println("30. Honey              €0.50");
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
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}