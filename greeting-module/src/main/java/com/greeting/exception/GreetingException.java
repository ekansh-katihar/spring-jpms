package com.greeting.exception;

/**
 * Custom exception for greeting module
 */
public class GreetingException extends RuntimeException {
    
    public GreetingException(String message) {
        super(message);
    }
    
    public GreetingException(String message, Throwable cause) {
        super(message, cause);
    }
}