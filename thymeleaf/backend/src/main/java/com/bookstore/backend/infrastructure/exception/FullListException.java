package com.bookstore.backend.infrastructure.exception;

public class FullListException extends Exception {
    private String message;

    public FullListException() {
        this.message = "Full list";
    }

    public FullListException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
}
