package com.pizzisalle.service;

import com.pizzisalle.exception.InvalidPizzaException;
import com.pizzisalle.model.pizza.base.Pizza;
import com.pizzisalle.model.pizza.types.*;
import com.pizzisalle.constants.Delegations;

/**
 * PATTERN: Factory Method - This class implements the Factory pattern to create different types
 * of pizzas without exposing the instantiation logic to the client. It also ensures that
 * location-specific pizzas are only created for their respective delegations.
 */
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

        // Validate location-specific pizzas
        validateExclusivePizza(type, location);

        // Create the requested pizza
        switch (type.toLowerCase()) {
            case "margherita":
                return new MargheritaPizza();
            case "hawaiian":
                return new HawaiianPizza();
            case "bacon_crispy":
                return new BaconCrispyPizza();
            case "american":
                return new AmericanPizza();
            case "traviata":
                return new TraviataPizza();
            case "burger":
                return new BurgerPizza();
            case "castellera":
                return new CastelleraPizza();
            case "cowboy":
                return new CowboyPizza();
            case "texas":
                return new TexasPizza();
            case "coast":
                return new CoastPizza();
            case "bbq":
                return new BBQPizza();
            case "diablo":
                return new DiabloPizza();
            case "carbonara":
                return new CarbonaraPizza();
            case "spanish":
                return new SpanishPizza();
            case "4_cheeses":
                return new FourCheesePizza();
            case "pepperoni":
                return new PepperoniPizza();
            case "vegetal":
                return new VegetalPizza();
            case "6_cheeses":
                return new SixCheesePizza();
            case "mallorca":
                return new MallorcaPizza();
            case "carbonara_deluxe":
                return new CarbonaraDeluxePizza();
            case "barcelona":
                if (location.equalsIgnoreCase(Delegations.BARCELONA.getName())) {
                    return new BarcelonaPizza();
                }
                throw new InvalidPizzaException("Barcelona pizza is only available in Barcelona delegation");
            case "tarragona":
                if (location.equalsIgnoreCase(Delegations.TARRAGONA.getName())) {
                    return new TarragonaPizza();
                }
                throw new InvalidPizzaException("Tarragona pizza is only available in Tarragona delegation");
            case "lleida":
                if (location.equalsIgnoreCase(Delegations.LLEIDA.getName())) {
                    return new LleidaPizza();
                }
                throw new InvalidPizzaException("Lleida pizza is only available in Lleida delegation");
            case "girona":
                if (location.equalsIgnoreCase(Delegations.GIRONA.getName())) {
                    return new GironaPizza();
                }
                throw new InvalidPizzaException("Girona pizza is only available in Girona delegation");
            default:
                throw new InvalidPizzaException("Invalid pizza type: " + type);
        }
    }

    private void validateExclusivePizza(String type, String location) throws InvalidPizzaException {
        // Validate that the location exists
        boolean validLocation = false;
        for (Delegations delegation : Delegations.values()) {
            if (delegation.getName().equalsIgnoreCase(location)) {
                validLocation = true;
                break;
            }
        }
        if (!validLocation) {
            throw new InvalidPizzaException("Invalid location: " + location);
        }

        // Check if trying to order an exclusive pizza from wrong location
        String pizzaType = type.toLowerCase();
        if ((pizzaType.equals("barcelona") && !location.equalsIgnoreCase("Barcelona")) ||
            (pizzaType.equals("girona") && !location.equalsIgnoreCase("Girona")) ||
            (pizzaType.equals("lleida") && !location.equalsIgnoreCase("Lleida")) ||
            (pizzaType.equals("tarragona") && !location.equalsIgnoreCase("Tarragona"))) {
            throw new InvalidPizzaException(
                "The " + type + " pizza is exclusive to " + type + " delegation");
        }
    }
}