package com.bookstore.backend.infrastructure.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Not found";
    }
    
}
