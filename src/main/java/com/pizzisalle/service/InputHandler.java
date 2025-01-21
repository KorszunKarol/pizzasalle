package com.pizzisalle.service;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import com.pizzisalle.constants.Beverage;
import com.pizzisalle.constants.Delegations;
import com.pizzisalle.exception.InvalidPizzaException;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import com.pizzisalle.model.customer.Customer;
import java.util.Random;

public class InputHandler {
    private final Scanner scanner;
    private Menu menu;
    private static final String PHONE_REGEX = "^\\d{9}$";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int MINIMUM_AGE_FOR_BEER = 18;
    private final PizzaFactory pizzaFactory;
    private final DatabaseManager dbManager;

    public InputHandler(Menu menu) {
        this.scanner = new Scanner(System.in);
        this.menu = menu;
        this.pizzaFactory = PizzaFactory.getInstance();
        this.dbManager = DatabaseManager.getInstance();
    }

    public InputHandler() {
        this.scanner = new Scanner(System.in);
        this.pizzaFactory = PizzaFactory.getInstance();
        this.dbManager = DatabaseManager.getInstance();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public static InputHandler createInputHandler() {
        InputHandler inputHandler = new InputHandler(null);
        Menu menu = new Menu(inputHandler);
        inputHandler.setMenu(menu);
        return inputHandler;
    }

    public Menu getMenu() {
        return menu;
    }

    public Customer readCustomerInfo() {
        System.out.println("\n=== Customer Registration ===");
        DatabaseManager dbManager = DatabaseManager.getInstance();
        String phone = readPhoneNumber();
        Customer existingCustomer = dbManager.findCustomerByPhone(phone);

        if (existingCustomer != null) {
            System.out.println("Welcome back, " + existingCustomer.getName() + "!");
            return existingCustomer;
        }

        System.out.println("\nNew customer registration:");
        String name = readString("Enter name:");
        String address = readString("Enter delivery address:");
        int age = readAge();
        Delegations delegation = Delegations.values()[new Random().nextInt(Delegations.values().length)];
        Customer newCustomer = new Customer(name, phone, address, age, delegation, true);
        dbManager.saveCustomer(newCustomer);
        return newCustomer;
    }

    private int readAge() {
        while (true) {
            try {
                System.out.println("\nAge must be between 14 and 100");
                int age = readInt("Enter your age: ");
                if (age >= 14 && age <= 100) {
                    return age;
                }
                System.out.println("❌ Age must be between 14 and 100");
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public String readPhoneNumber() {
        while (true) {
            String phone = readString("Enter your phone number (9 digits): ").trim();
            if (phone.matches("\\d{9}")) { 
                return phone;
            }
            System.out.println("❌ Invalid phone number. Must be exactly 9 digits (e.g., 123456789)");
        }
    }

    public int readPizzaSelection(int maxOptions) {
        return readInteger("Select a pizza (enter number): ", 1, maxOptions);
    }

    public boolean readYesNoInput(String prompt) {
        while (true) {
            String input = readString(prompt + " (y/n): ").toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Please enter 'y' for yes or 'n' for no");
        }
    }

    public int readInteger(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public Pizza readPizzaSelection(Delegations delegation) {
        while (true) {
            String pizzaName = readString("Enter pizza name (or 'done' to finish): ");

            if (pizzaName.equalsIgnoreCase("done")) {
                return null;
            }

            try {
                return PizzaFactory.getInstance().createPizza(pizzaName, delegation.getName());
            } catch (InvalidPizzaException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again or type 'done' to finish.");
            }
        }
    }

    public Ingredient readIngredientChoice() {
        while (true) {
            try {
                menu.displayExtraIngredients();
                int choice = readInteger("Select extra ingredient: ", 0, 30);

                if (choice == 0) {
                    return null;
                }

                String ingredientName = switch (choice) {
                    case 1 -> "Cheese";
                    case 2 -> "Ham";
                    case 3 -> "Chicken";
                    case 4 -> "Bacon";
                    case 5 -> "Beef";
                    case 6 -> "Frankfurt";
                    case 7 -> "Pepperoni";
                    case 8 -> "Jamón Serrano";
                    case 9 -> "Miniburgers";
                    case 10 -> "Sausage";
                    case 11 -> "Sobrassada";
                    case 12 -> "Tuna";
                    case 13 -> "Anchovies";
                    case 14 -> "Prawns";
                    case 15 -> "Onion";
                    case 16 -> "Bell Pepper";
                    case 17 -> "Tomato slices";
                    case 18 -> "Artichoke";
                    case 19 -> "Mushrooms";
                    case 20 -> "Olives";
                    case 21 -> "Pineapple";
                    case 22 -> "Mozzarella";
                    case 23 -> "Goat Cheese";
                    case 24 -> "Brie";
                    case 25 -> "Emmental";
                    case 26 -> "Roquefort";
                    case 27 -> "Cheddar";
                    case 28 -> "BBQ Sauce";
                    case 29 -> "Carbonara sauce";
                    case 30 -> "Honey";
                    default -> throw new IllegalStateException("Invalid ingredient choice: " + choice);
                };

                return IngredientFactory.getInstance().getIngredient(ingredientName);
            } catch (Exception e) {
                System.out.println("❌ Error selecting ingredient: " + e.getMessage());
                System.out.println("Please try again or select 0 to cancel.");
            }
        }
    }

    public Beverage readBeverageChoice(Customer customer) {
        return menu.displayBeverages(customer);
    }

    public int readQuantity(String item) {
        return readInteger("Enter quantity for " + item + ": ", 1, 10);
    }

    public int readMenuChoice() {
        return readInteger("Enter your choice: ", 0, 9);
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public void close() {
        scanner.close();
    }
}