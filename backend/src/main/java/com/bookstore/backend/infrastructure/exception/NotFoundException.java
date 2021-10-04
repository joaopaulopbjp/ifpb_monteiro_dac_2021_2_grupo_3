package com.bookstore.backend.infrastructure.exception;

public class NotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Not found";
    }
    
}
