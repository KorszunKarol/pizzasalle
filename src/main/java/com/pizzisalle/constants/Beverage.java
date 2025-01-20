package com.pizzisalle.constants;

public enum Beverage {
    WATER(1.0),
    SODA(2.0),
    BEER(3.0);

    private final double price;

    Beverage(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}