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

        int age = readInteger("Enter age: ", 1, 120);

        return new Customer(name, phone, address, age);
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
        private final int age;

        public CustomerInfo(String name, String email, String phone, String address, int age) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.age = age;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
        public int getAge() { return age; }
    }

    public void close() {
        scanner.close();
    }
}