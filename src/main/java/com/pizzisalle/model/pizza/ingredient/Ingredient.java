package com.pizzisalle.model.pizza.ingredient;

public class Ingredient {
    private final String name;
    private final int priceInCents;

    public Ingredient(String name, int priceInCents) {
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public String getName() {
        return name;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public String getFormattedPrice() {
        return String.format("€%.2f", priceInCents / 100.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient that = (Ingredient) obj;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}