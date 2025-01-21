package com.pizzisalle.test;

import com.pizzisalle.constants.Delegations;
import com.pizzisalle.service.Menu;
import com.pizzisalle.service.InputHandler;
import java.util.Scanner;
import com.pizzisalle.model.customer.Customer;

public class MenuTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        Menu menu = new Menu(inputHandler);
        inputHandler.setMenu(menu);
        Customer testCustomer = new Customer("Test User", "123456789", "Test Address", 20, Delegations.BARCELONA, true);

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
        menu.displayBeverages(testCustomer);
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