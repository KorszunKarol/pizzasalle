package com.pizzisalle.model.pizza.ingredient;

public class IngredientQuantity {
    private final Ingredient ingredient;
    private int quantity;
    private static final int MAX_QUANTITY = 10;

    public IngredientQuantity(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        setQuantity(quantity);
    }

    public void incrementQuantity() {
        setQuantity(quantity + 1);
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Cannot add more than " + MAX_QUANTITY + " of an ingredient");
        }
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPriceInCents() {
        return ingredient.getPriceInCents() * quantity;
    }

    public String getFormattedTotalPrice() {
        return String.format("€%.2f", getTotalPriceInCents() / 100.0);
    }
}