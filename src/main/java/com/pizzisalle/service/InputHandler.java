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

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^\\d{9}$";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int MINIMUM_AGE_FOR_BEER = 18;
    private final Menu menu;
    private final PizzaFactory pizzaFactory;

    public InputHandler() {
        this.menu = new Menu();
        this.pizzaFactory = PizzaFactory.getInstance();
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInteger(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public Customer readCustomerInfo() {
        String name = readString("Enter your name: ");
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            name = readString("Enter your name: ");
        }

        String email = readEmail();
        String phone = readPhone();
        String address = readString("Enter your address: ");
        while (address.isEmpty()) {
            System.out.println("Address cannot be empty");
            address = readString("Enter your address: ");
        }

        System.out.print("Is this your first order? (y/n): ");
        boolean firstOrder = scanner.nextLine().toLowerCase().startsWith("y");

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        return new Customer(name, phone, address, firstOrder, age);
    }

    private String readEmail() {
        while (true) {
            String email = readString("Enter your email: ");
            if (Pattern.matches(EMAIL_REGEX, email)) {
                return email;
            }
            System.out.println("Please enter a valid email address");
        }
    }

    private String readPhone() {
        while (true) {
            String phone = readString("Enter your phone number (9 digits): ");
            if (Pattern.matches(PHONE_REGEX, phone)) {
                return phone;
            }
            System.out.println("Please enter a valid 9-digit phone number");
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

    public boolean validateAge() {
        while (true) {
            try {
                String birthDate = readString("Enter your birth date (dd/MM/yyyy): ");
                LocalDate dob = LocalDate.parse(birthDate, DATE_FORMATTER);
                LocalDate now = LocalDate.now();
                int age = Period.between(dob, now).getYears();

                if (age >= MINIMUM_AGE_FOR_BEER) {
                    return true;
                }
                System.out.println("You must be at least 18 years old to order beer");
                return false;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in the format dd/MM/yyyy");
            }
        }
    }

    public int readQuantity(String item) {
        return readInteger("Enter quantity for " + item + ": ", 1, 10);
    }

    public int readMenuChoice() {
        return readInteger("Enter your choice: ", 0, 9);
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
                int choice = readInteger("Select extra ingredient: ", 0, 7);

                if (choice == 0) {
                    return null;  // User chose to finish
                }

                // Map numeric choices to ingredient names as defined in IngredientFactory
                Ingredient ingredient = switch (choice) {
                    case 1 -> IngredientFactory.getInstance().getIngredient("Cheese");
                    case 2 -> IngredientFactory.getInstance().getIngredient("Mushrooms");
                    case 3 -> IngredientFactory.getInstance().getIngredient("Pepperoni");
                    case 4 -> IngredientFactory.getInstance().getIngredient("Ham");
                    case 5 -> IngredientFactory.getInstance().getIngredient("Pineapple");
                    case 6 -> IngredientFactory.getInstance().getIngredient("Chicken");
                    case 7 -> IngredientFactory.getInstance().getIngredient("Bell Pepper"); // Changed from MIXED_VEGETABLES
                    default -> throw new IllegalStateException("Invalid ingredient choice: " + choice);
                };

                return ingredient;
            } catch (Exception e) {
                System.out.println("❌ Error selecting ingredient: " + e.getMessage());
                System.out.println("Please try again or select 0 to cancel.");
            }
        }
    }

    public Beverage readBeverageChoice(int customerAge) {
        while (true) {
            try {
                menu.displayBeverages();
                int choice = readInteger("Enter your choice: ", 0, 3);

                switch (choice) {
                    case 0: return null;
                    case 1: return Beverage.WATER;
                    case 2: return Beverage.SODA;
                    case 3:
                        if (customerAge < 18) {
                            System.out.println("Must be 18 or older to order beer");
                            continue;
                        }
                        return Beverage.BEER;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    public static class CustomerInfo {
        private final String name;
        private final String email;
        private final String phone;
        private final String address;
        private final boolean firstOrder;
        private final int age;

        public CustomerInfo(String name, String email, String phone, String address, boolean firstOrder, int age) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.firstOrder = firstOrder;
            this.age = age;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
        public boolean isFirstOrder() { return firstOrder; }
        public int getAge() { return age; }
    }

    public void close() {
        scanner.close();
    }
}