package com.greeting.sharedimpl;

import com.greeting.shared.SharedGreetingService;
import org.springframework.stereotype.Service;

/**
 * Implementation of shared greeting service
 */
@Service
public class SharedGreetingServiceImpl implements SharedGreetingService {
    
    @Override
    public String englishGreet() {
        return "Hello! Welcome to our application!";
    }
}