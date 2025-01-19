# PizziSalle Management System

A comprehensive pizza ordering and management system for PizziSalle restaurants, implementing various design patterns and best practices in Java.

## Table of Contents
- [Overview](#overview)
- [Design Patterns](#design-patterns)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [Component Interactions](#component-interactions)
- [Usage Examples](#usage-examples)

## Overview

PizziSalle is a pizza restaurant management system that handles orders, menu management, and delegations. The system is built using Java and implements various design patterns to ensure maintainable, extensible, and robust code.

## Design Patterns

The project implements several design patterns to solve specific architectural challenges:

1. **Factory Pattern** (`PizzaFactory`)
   - Used for creating different types of pizzas
   - Encapsulates pizza creation logic
   - Makes adding new pizza types easier

2. **Decorator Pattern** (`PizzaDecorator`)
   - Allows dynamic modification of pizzas
   - Handles extra ingredients
   - Enables ingredient quantity modifications

3. **Builder Pattern** (`OrderBuilder`)
   - Simplifies order creation process
   - Handles complex order construction
   - Ensures order validity

4. **Singleton Pattern** (`DelegationManager`)
   - Manages restaurant delegations
   - Ensures single point of control
   - Maintains consistent delegation state

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── pizzisalle/
│               ├── model/
│               │   ├── pizza/
│               │   ├── order/
│               │   └── delegation/
│               ├── factory/
│               ├── decorator/
│               ├── builder/
│               └── util/
└── test/
    └── java/
        └── com/
            └── pizzisalle/
```

## Setup Instructions

1. Prerequisites:
   - Java JDK 11 or higher
   - Maven 3.6.0 or higher

2. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/pizzisalle.git
   ```

3. Navigate to project directory:
   ```bash
   cd pizzisalle
   ```

4. Install dependencies:
   ```bash
   mvn install
   ```

## Running the Application

1. Compile the project:
   ```bash
   mvn compile
   ```

2. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.pizzisalle.Main"
   ```

3. Run tests:
   ```bash
   mvn test
   ```

## Component Interactions

The system follows a modular architecture where components interact through well-defined interfaces:

1. **Order Flow**:
   - Client → OrderBuilder → Order → PizzaFactory → Pizza
   - Pizza → PizzaDecorator (for modifications)
   - Order → DelegationManager (for delegation assignment)

2. **Delegation Management**:
   - DelegationManager handles delegation assignment
   - Each delegation has specific pizza types and rules
   - Orders are validated against delegation rules

## Usage Examples

1. Creating a basic order:
```java
Order order = new OrderBuilder()
    .addPizza("Margherita")
    .setCustomer("John Doe")
    .build();
```

2. Adding extra ingredients:
```java
Pizza pizza = PizzaFactory.createPizza("Margherita");
pizza = new ExtraCheeseDecorator(pizza);
```

3. Managing delegations:
```java
DelegationManager.getInstance().assignDelegation(order);
```

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.