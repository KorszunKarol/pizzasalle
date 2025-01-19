# PizziSalle System Design Documentation

## Core Classes and Interfaces

### 1. Domain Models

#### `Pizza` (Abstract Class)
```java
public abstract class Pizza {
    protected String name;
    protected List<String> ingredients;
    protected double basePrice;
    protected boolean isExclusive;

    public abstract double calculatePrice();
    public abstract List<String> getIngredients();
}
```
**Justification**: Base class for all pizza types. Abstract to enforce implementation of core methods by concrete pizza classes.

#### `Customer`
```java
public class Customer {
    private String name;
    private String phone;
    private String address;
    private boolean firstOrder;
    private String assignedDelegation;

    // Constructor, getters, setters
}
```
**Justification**: Encapsulates customer information and their assigned delegation.

#### `Order`
```java
public class Order {
    private Customer customer;
    private List<Pizza> pizzas;
    private CrustType crust;
    private Beverage beverage;
    private double totalPrice;

    public double calculateTotal();
    public void addPizza(Pizza pizza);
}
```
**Justification**: Represents a customer's order with all necessary details.

### 2. Design Patterns Implementation

#### `PizzaFactory` (Factory Pattern)
```java
public class PizzaFactory {
    // PATTERN: Factory Pattern
    public Pizza createPizza(String type, String location) {
        if (type.equals("Barcelona") && location.equals("Barcelona")) {
            return new BarcelonaPizza();
        }
        // Other pizza types...
    }
}
```
**Justification**: Creates pizza instances without exposing creation logic. Handles both standard and location-specific pizzas.

#### `PizzaDecorator` (Decorator Pattern)
```java
public abstract class PizzaDecorator extends Pizza {
    // PATTERN: Decorator Pattern
    protected Pizza pizza;

    public void addExtraIngredient(String ingredient);
    public void increaseIngredientQuantity(String ingredient);
}
```
**Justification**: Enables dynamic modification of pizzas with extra ingredients.

#### `OrderBuilder` (Builder Pattern)
```java
public class OrderBuilder {
    // PATTERN: Builder Pattern
    private Order order = new Order();

    public OrderBuilder addPizza(Pizza pizza);
    public OrderBuilder setCrust(CrustType crust);
    public OrderBuilder setBeverage(Beverage beverage);
    public Order build();
}
```
**Justification**: Provides step-by-step construction of complex Order objects.

#### `DelegationManager` (Singleton Pattern)
```java
public class DelegationManager {
    // PATTERN: Singleton Pattern
    private static DelegationManager instance;
    private Random random = new Random();

    public static DelegationManager getInstance();
    public String assignDelegation();
}
```
**Justification**: Ensures single point of control for delegation assignment.

### 3. Enums and Constants

#### `CrustType`
```java
public enum CrustType {
    ORIGINAL,
    THIN,
    SICILIAN
}
```

#### `Beverage`
```java
public enum Beverage {
    WATER,
    SODA,
    BEER
}
```

### 4. Utility Classes

#### `InputHandler`
```java
public class InputHandler {
    private Scanner scanner;

    public Customer readCustomerInfo();
    public Pizza readPizzaSelection();
    public boolean validateAge(int age);
}
```
**Justification**: Manages and validates all user input from terminal.

#### `Menu`
```java
public class Menu {
    public void displayMainMenu();
    public void displayPizzaMenu();
    public void displayBeverages();
    public void displayCrustTypes();
}
```
**Justification**: Handles all terminal display functionality.

### 5. Main Application

#### `MainApp`
```java
public class MainApp {
    private PizzaFactory pizzaFactory;
    private DelegationManager delegationManager;
    private InputHandler inputHandler;
    private Menu menu;

    public void start() {
        // Main application loop
    }

    public void processOrder(Order order) {
        // Order processing logic
    }
}
```
**Justification**: Entry point and main controller of the application.

## Implementation Notes

1. **Terminal Interface**
   - All user interaction through console
   - Clear menu structure
   - Input validation

2. **Data Storage**
   - In-memory storage using collections
   - Optional SQLite implementation

3. **Validation Rules**
   - Maximum 10 items per element
   - Age verification for beer orders
   - Valid delegation assignment

## Design Pattern Usage

1. **Factory Pattern**: Pizza creation
2. **Decorator Pattern**: Pizza customization
3. **Builder Pattern**: Order construction
4. **Singleton Pattern**: Delegation management

## Error Handling

- Input validation
- Age verification
- Delegation assignment validation
- Order limit validation