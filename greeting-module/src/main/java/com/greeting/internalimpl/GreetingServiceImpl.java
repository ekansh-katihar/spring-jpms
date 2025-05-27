package com.greeting.internalimpl;

import com.greeting.internal.GreetingService;
import org.springframework.stereotype.Service;

/**
 * Implementation of internal greeting service
 */
@Service
public class GreetingServiceImpl implements GreetingService {
    
    @Override
    public String englishGreet() {
        return "Hello! Welcome to our application!";
    }
    
    @Override
    public String frenchGreet() {
        return "Bonjour! Bienvenue dans notre application!";
    }
}