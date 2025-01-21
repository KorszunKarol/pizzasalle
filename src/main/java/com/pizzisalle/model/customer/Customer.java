package com.pizzisalle.model.customer;

import com.pizzisalle.constants.Delegations;

/**
 * PATTERN: Immutable Object - The Customer class is designed as an immutable object
 * to ensure thread safety and prevent accidental modification of customer data.
 */
public class Customer {
    private final String name;
    private final String phone;
    private final String address;
    private final int age;
    private final Delegations delegation;
    private final boolean firstOrder;

    public Customer(String name, String phone, String address, int age, Delegations delegation, boolean firstOrder) {
        validateAge(age);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.delegation = delegation;
        this.firstOrder = firstOrder;
    }

    private void validateAge(int age) {
        if (age < 14 || age > 100) {
            throw new IllegalArgumentException("Age must be between 14 and 100");
        }
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public Delegations getDelegation() {
        return delegation;
    }

    public boolean isFirstOrder() {
        return firstOrder;
    }

    public boolean isLegalForBeer() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return String.format("Customer: %s, Phone: %s, Address: %s, Age: %d, Delegation: %s",
            name, phone, address, age, delegation);
    }
}