package com.friendlyCarsSystem.friendly_cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ShoppringCartException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException {

    public ShoppingCartNotFoundException() {
        super();
    }

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }

    public ShoppingCartNotFoundException(Throwable cause) {
        super(cause);
    }

    public ShoppingCartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShoppingCartNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
