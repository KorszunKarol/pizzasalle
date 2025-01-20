package com.pizzisalle.model.pizza;

import java.util.List;
import java.util.Map;
import com.pizzisalle.model.pizza.ingredient.Ingredient;
import com.pizzisalle.constants.CrustType;

/**
 * PATTERN: Component - This interface defines the common interface for both Pizza objects
 * and their decorators, allowing them to be treated uniformly.
 */
public interface PizzaComponent {
    String getName();
    double calculatePrice();
    List<String> getIngredients();
    List<String> getExtraIngredients();
    Map<String, Integer> getIngredientQuantities();
    double getExtrasPrice();
    CrustType getCrustType();
    void setCrustType(CrustType type);
    String getFormattedPrice();
}