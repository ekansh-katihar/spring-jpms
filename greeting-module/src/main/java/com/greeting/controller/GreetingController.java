package com.greeting.controller;

import com.greeting.internal.GreetingService;
import com.greeting.exception.GreetingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * REST controller for greeting endpoints
 */
@RestController
public class GreetingController {
    
    private final GreetingService greetingService;
    
    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    
    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        try {
            String greeting = greetingService.frenchGreet();
            return ResponseEntity.ok(greeting);
        } catch (Exception e) {
            throw new GreetingException("Failed to generate greeting", e);
        }
    }
    
    /**
     * Exception advice for greeting controller
     */
    @ControllerAdvice
    public static class GreetingExceptionAdvice {
        
        @ExceptionHandler(GreetingException.class)
        public ResponseEntity<String> handleGreetingException(GreetingException e) {
            return ResponseEntity.internalServerError()
                .body("Greeting Error: " + e.getMessage());
        }
        
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception e) {
            return ResponseEntity.internalServerError()
                .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}