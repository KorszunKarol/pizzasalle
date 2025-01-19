package com.pizzisalle.service;

public class Menu {
    private static final String SEPARATOR = "----------------------------------------";
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void printHeader(String title) {
        System.out.println(SEPARATOR);
        System.out.println("              " + title);
        System.out.println(SEPARATOR);
    }

    public static void displayMainMenu() {
        clearScreen();
        printHeader("PIZZISALLE - MAIN MENU");
        System.out.println("1. Order Pizza");
        System.out.println("2. Order Beverages");
        System.out.println("3. View Current Order");
        System.out.println("4. Confirm Order");
        System.out.println("0. Exit");
        System.out.println(SEPARATOR);
        System.out.print("Please select an option: ");
    }

    public static void displayPizzaMenu() {
        clearScreen();
        printHeader("AVAILABLE PIZZAS");
        System.out.println("1. Margherita - Tomato, Mozzarella, Basil");
        System.out.println("2. Pepperoni - Tomato, Mozzarella, Pepperoni");
        System.out.println("3. Hawaiian - Tomato, Mozzarella, Ham, Pineapple");
        System.out.println("4. BBQ Chicken - BBQ Sauce, Mozzarella, Chicken");
        System.out.println("5. Veggie - Tomato, Mozzarella, Mixed Vegetables");
        System.out.println("6. Custom Pizza");
        System.out.println("0. Back to Main Menu");
        System.out.println(SEPARATOR);
        System.out.print("Select your pizza: ");
    }

    public static void displayBeverages() {
        clearScreen();
        printHeader("AVAILABLE BEVERAGES");
        System.out.println("1. Water (0.5L) - €1.50");
        System.out.println("2. Soda (0.33L) - €1.95");
        System.out.println("3. Beer (0.33L) - €2.50");
        System.out.println("0. Back to Main Menu");
        System.out.println(SEPARATOR);
        System.out.print("Select your beverage: ");
    }

    public static void displayCrustTypes() {
        clearScreen();
        printHeader("AVAILABLE CRUST TYPES");
        System.out.println("1. Original");
        System.out.println("2. Thin");
        System.out.println("3. Thick");
        System.out.println("4. Stuffed");
        System.out.println("0. Back");
        System.out.println(SEPARATOR);
        System.out.print("Select crust type: ");
    }

    public static void displayExtraIngredients() {
        clearScreen();
        printHeader("EXTRA INGREDIENTS");
        System.out.println("1. Extra Cheese - €1.00");
        System.out.println("2. Mushrooms - €0.50");
        System.out.println("3. Pepperoni - €0.75");
        System.out.println("4. Ham - €0.75");
        System.out.println("5. Pineapple - €0.50");
        System.out.println("6. Chicken - €1.00");
        System.out.println("7. Mixed Vegetables - €0.75");
        System.out.println("0. Done adding extras");
        System.out.println(SEPARATOR);
        System.out.print("Select extra ingredient: ");
    }

    public static void displayOrderSummary(String orderDetails) {
        clearScreen();
        printHeader("ORDER SUMMARY");
        System.out.println(orderDetails);
        System.out.println(SEPARATOR);
        System.out.println("1. Confirm Order");
        System.out.println("2. Modify Order");
        System.out.println("0. Cancel Order");
        System.out.println(SEPARATOR);
        System.out.print("Select an option: ");
    }

    public static void displayError(String message) {
        System.out.println("\nERROR: " + message);
        System.out.println("Press Enter to continue...");
    }

    public static void displaySuccess(String message) {
        System.out.println("\nSUCCESS: " + message);
        System.out.println("Press Enter to continue...");
    }
}