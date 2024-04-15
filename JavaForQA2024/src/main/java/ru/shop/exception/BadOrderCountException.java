package ru.shop.exception;

public class BadOrderCountException extends RuntimeException {

    public BadOrderCountException(Long count) {
        super("Order must be positive: " + count);
    }
}
