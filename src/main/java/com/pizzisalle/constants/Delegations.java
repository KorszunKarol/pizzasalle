package com.pizzisalle.constants;

/**
 * Enumeration representing the available PizziSalle delegations.
 * This enum ensures type safety and consistency when referring to delegations throughout the application.
 */
public enum Delegations {
    BARCELONA("Barcelona"),
    GIRONA("Girona"),
    LLEIDA("Lleida"),
    TARRAGONA("Tarragona");

    private final String name;

    Delegations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}