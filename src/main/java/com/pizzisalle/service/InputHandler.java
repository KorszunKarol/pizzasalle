package com.pizzisalle.service;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^\\d{9}$";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final int MINIMUM_AGE_FOR_BEER = 18;

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

    public CustomerInfo readCustomerInfo() {
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

        return new CustomerInfo(name, email, phone, address);
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

    public static class CustomerInfo {
        private final String name;
        private final String email;
        private final String phone;
        private final String address;

        public CustomerInfo(String name, String email, String phone, String address) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getAddress() { return address; }
    }

    public void close() {
        scanner.close();
    }
}