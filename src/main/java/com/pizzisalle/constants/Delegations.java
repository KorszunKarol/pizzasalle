package com.pizzisalle.constants;

/**
 * PATTERN: Enumeration - This enum ensures type safety and consistency when referring to delegations
 * throughout the application, following the type-safe enum pattern.
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