package com.pizzisalle.constants;

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