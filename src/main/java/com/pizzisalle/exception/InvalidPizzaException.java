package com.pizzisalle.exception;

public class InvalidPizzaException extends RuntimeException {
    public InvalidPizzaException(String message) {
        super(message);
    }
}