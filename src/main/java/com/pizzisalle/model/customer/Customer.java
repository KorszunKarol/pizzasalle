package com.pizzisalle.model.customer;

import com.pizzisalle.constants.Delegations;

public class Customer {
    private String name;
    private String phone;
    private String address;
    private boolean firstOrder;
    private Delegations delegation;
    private int age;

    public Customer(String name, String phone, String address, boolean firstOrder, int age) {
        setName(name);
        setPhone(phone);
        setAddress(address);
        setAge(age);
        this.firstOrder = firstOrder;
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{9}")) {
            throw new IllegalArgumentException("Phone number must be 9 digits");
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

    public boolean isFirstOrder() {
        return firstOrder;
    }

    public Delegations getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegations delegation) {
        this.delegation = delegation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be a positive number");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address.trim();
    }

    public void setFirstOrder(boolean firstOrder) {
        this.firstOrder = firstOrder;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}