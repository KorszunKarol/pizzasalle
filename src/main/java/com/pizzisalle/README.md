# PizziSalle Management System

[![Build Status](https://img.shields.io/travis/pizzisalle/pizzisalle/main.svg)](https://travis-ci.org/pizzisalle/pizzisalle)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)
[![Version](https://img.shields.io/badge/version-1.0.0-green.svg)]()

A comprehensive pizza ordering and management system for PizziSalle restaurants, implementing various design patterns and best practices in Java.

## Table of Contents
- [Overview](#overview)
- [Design Patterns](#design-patterns)
- [Features](#features)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Quick Start Guide](#quick-start-guide)
- [Running the Application](#running-the-application)
- [Component Interactions](#component-interactions)
- [Usage Examples](#usage-examples)
- [Troubleshooting](#troubleshooting)

## Overview

PizziSalle is a pizza restaurant management system that handles orders, menu management, and delegations. The system is built using Java and implements various design patterns to ensure maintainable, extensible, and robust code.

## Features

### Menu Management
- Comprehensive pizza menu with detailed ingredient lists and pricing
- Special delegation-exclusive pizzas for Barcelona, Girona, Tarragona, and Lleida
- Customizable pizzas with extra ingredients and quantity modifications
- Maximum order limits: 10 pizzas and 10 beverages per order

### Beverage Options
- Various drink choices including water, soda, and beer
- Age verification system for alcoholic beverages (18+ required)

### Pizza Customization
- Multiple crust types available
- Extra ingredients option with individual pricing
- Ingredient quantity modifications
- Maximum 10 extra ingredients per pizza

### Order Management
- Smart delegation system for order routing
- Customer profile management
- Age verification for restricted items
- Order validation and processing
- Real-time order status tracking

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
## Setup Instructions

1. Prerequisites:
   - Java JDK 11 or higher (recommended: OpenJDK 11.0.12)
   - Maven 3.6.0 or higher (recommended: 3.8.4)
   - Git (for cloning the repository)

2. Verify Prerequisites:
   ```bash
   java -version
   mvn -version
   git --version
   ```

3. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/pizzisalle.git
   ```

4. Navigate to project directory:
   ```bash
   cd pizzisalle
   ```

5. Install dependencies:
   ```bash
   mvn install
   ```

6. Verify installation:
   ```bash
   mvn verify
   ```

Common setup issues:
- If Java is not found, ensure JAVA_HOME is set correctly
- For Maven errors, check settings.xml configuration
- For dependency issues, try `mvn clean install -U`

## Running the Application

1. Compile the project:
   ```bash

## Quick Start Guide

1. Start the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.pizzisalle.Main"
   ```

2. Create your first order:
   - Select "New Order" from the main menu
   - Enter your customer information
   - Choose pizzas from the menu (max 10)
   - Add extra ingredients if desired
   - Select beverages (max 10)
   - Review and confirm your order
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
   - Select "New Order" from the main menu
   - Choose a Margherita pizza
   - Add a beverage
   - Enter delivery information
   - Confirm order

2. Adding extra ingredients:
   - Select a pizza from the menu
   - Choose "Add extra ingredient"
   - Select desired extras (cheese, mushrooms, etc.)
   - Confirm customizations

3. Managing delegations:
   - System automatically assigns orders to the appropriate delegation
   - Barcelona delegation handles exclusive pizzas
   - Orders are routed based on customer location

## Troubleshooting

### Common Issues

1. Order Creation Problems:
   - Ensure you don't exceed 10 pizzas per order
   - Verify age requirements for alcoholic beverages
   - Check delegation availability for exclusive pizzas

2. System Errors:
   - Verify Java version compatibility
   - Check log files in `/logs` directory
   - Ensure all dependencies are properly installed

3. Payment Processing:
   - Confirm order total calculation
   - Verify payment method availability
   - Check network connectivity

For additional support, please:
1. Check the logs
2. Review error messages
3. Contact support team

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

For detailed guidelines, please read [CONTRIBUTING.md](CONTRIBUTING.md)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---
© 2024 PizziSalle. All rights reserved.