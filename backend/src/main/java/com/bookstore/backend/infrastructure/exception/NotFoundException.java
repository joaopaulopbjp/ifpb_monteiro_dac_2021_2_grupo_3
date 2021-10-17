package com.bookstore.backend.infrastructure.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Not Found!");
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
