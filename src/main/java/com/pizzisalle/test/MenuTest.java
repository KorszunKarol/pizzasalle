package com.pizzisalle.test;

import com.pizzisalle.constants.Delegations;
import com.pizzisalle.service.Menu;
import java.util.Scanner;

public class MenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.println("\nTesting Main Menu:");
        menu.displayMainMenu();
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Pizza Menu:");
        menu.displayPizzaMenu(Delegations.BARCELONA);
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Crust Types Menu:");
        menu.displayCrustTypes();
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Beverages Menu:");
        menu.displayBeverages();
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Extra Ingredients Menu:");
        Menu.displayExtraIngredients();
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Order Summary:");
        Menu.displayOrderSummary("Test Order\nPizza: Margherita\nPrice: €8.00");
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Error Message:");
        Menu.displayError("This is a test error message");
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Success Message:");
        Menu.displaySuccess("This is a test success message");
        pressEnterToContinue(scanner);

        System.out.println("\nTesting Pizza Availability:");
        System.out.println("Is Margherita available? " + Menu.isPizzaAvailable("Margherita"));
        System.out.println("Is FakePizza available? " + Menu.isPizzaAvailable("FakePizza"));

        scanner.close();
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        Menu.clearScreen();
    }
}