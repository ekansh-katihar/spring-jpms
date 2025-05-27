package com.greeting.internal;

import com.greeting.shared.SharedGreetingService;

/**
 * Internal greeting service interface extending shared service
 */
public interface GreetingService extends SharedGreetingService {
    /**
     * Provides French greeting
     * @return French greeting message
     */
    String frenchGreet();
}