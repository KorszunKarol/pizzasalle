package com.pizzisalle.model.customer;

import com.pizzisalle.constants.Delegations;
import com.pizzisalle.service.DelegationManager;
import java.util.Random;

/**
 * PATTERN: Immutable Object - The Customer class is designed as an immutable object
 * to ensure thread safety and prevent accidental modification of customer data.
 */
public class Customer {
    private String name;
    private String phone;
    private String address;
    private int age;
    private Delegations delegation;
    private static final Random random = new Random();

    public Customer(String name, String phone, String address, int age) {
        setName(name);
        setPhone(phone);
        setAddress(address);
        this.age = age;
        assignRandomDelegation();
    }

    private void assignRandomDelegation() {
        DelegationManager delegationManager = DelegationManager.getInstance();
        this.delegation = delegationManager.assignRandomDelegation();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{9}")) {
            throw new IllegalArgumentException("Phone number must be 9 digits");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address.trim();
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public int getAge() { return age; }
    public Delegations getDelegation() { return delegation; }
    public boolean isAdult() { return age >= 18; }

    @Override
    public String toString() {
        return String.format("Customer{name='%s', phone='%s', address='%s', age=%d, delegation=%s}",
                name, phone, address, age, delegation.getName());
    }
}