package com.bookstore.backend.infrastructure.exception;

public class InvalidValueException extends Exception {

    public InvalidValueException() {
        super("Invalid Value!");
    }

    public InvalidValueException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
