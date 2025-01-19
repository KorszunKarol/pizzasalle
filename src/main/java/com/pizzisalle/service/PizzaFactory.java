package com.pizzisalle.service;

import com.pizzisalle.model.*;
import com.pizzisalle.exception.InvalidPizzaException;

public class PizzaFactory {
    // PATTERN: Singleton - Ensures only one instance of PizzaFactory exists
    private static PizzaFactory instance;
    
    private PizzaFactory() {}
    
    public static PizzaFactory getInstance() {
        if (instance == null) {
            instance = new PizzaFactory();
        }
        return instance;
    }
    
    // PATTERN: Factory Method - Creates different pizza objects based on type and location
    public Pizza createPizza(String type, String location) throws InvalidPizzaException {
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidPizzaException("Pizza type cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new InvalidPizzaException("Location cannot be null or empty");
        }
        
        type = type.toLowerCase();
        location = location.toLowerCase();
        
        // Validate exclusive pizzas
        validateExclusivePizza(type, location);
        
        switch (type) {
            case "margherita":
                return new Margherita();
            case "hawaiian":
                return new Hawaiian();
            case "vegetarian":
                return new Vegetarian();
            case "fourseasons":
                return new FourSeasons();
            case "barcelona":
                if ("barcelona".equals(location)) {
                    return new BarcelonaPizza();
                }
                throw new InvalidPizzaException("Barcelona pizza is only available in Barcelona delegation");
            case "tarragona":
                if ("tarragona".equals(location)) {
                    return new TarragonaPizza();
                }
                throw new InvalidPizzaException("Tarragona pizza is only available in Tarragona delegation");
            case "lleida":
                if ("lleida".equals(location)) {
                    return new LleidaPizza();
                }
                throw new InvalidPizzaException("Lleida pizza is only available in Lleida delegation");
            case "girona":
                if ("girona".equals(location)) {
                    return new GironaPizza();
                }
                throw new InvalidPizzaException("Girona pizza is only available in Girona delegation");
            default:
                throw new InvalidPizzaException("Invalid pizza type: " + type);
        }
    }
    
    private void validateExclusivePizza(String type, String location) throws InvalidPizzaException {
        String[] exclusivePizzas = {"barcelona", "tarragona", "lleida", "girona"};
        String[] validLocations = {"barcelona", "tarragona", "lleida", "girona"};
        
        // Validate location
        boolean validLocation = false;
        for (String loc : validLocations) {
            if (loc.equals(location)) {
                validLocation = true;
                break;
            }
        }
        if (!validLocation) {
            throw new InvalidPizzaException("Invalid location: " + location);
        }
        
        // Check if trying to order exclusive pizza from wrong location
        for (String exclusivePizza : exclusivePizzas) {
            if (type.equals(exclusivePizza) && !location.equals(type)) {
                throw new InvalidPizzaException(
                    String.format("%s pizza is only available in %s delegation", 
                        type.substring(0, 1).toUpperCase() + type.substring(1),
                        type.substring(0, 1).toUpperCase() + type.substring(1)
                    )
                );
            }
        }
    }
}