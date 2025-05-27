package com.chat.internalimpl;

import com.chat.internal.ChatService;
// import com.greeting.shared.SharedGreetingService;
import org.springframework.stereotype.Service;
import java.util.ServiceLoader;

/**
 * Implementation of chat service
 */
@Service
public class ChatServiceImpl implements ChatService {
    
    // private final SharedGreetingService sharedGreetingService;
    
    public ChatServiceImpl() {
        
        // Load service using JPMS ServiceLoader
        // ServiceLoader<SharedGreetingService> serviceLoader = 
        //     ServiceLoader.load(SharedGreetingService.class);
        // this.sharedGreetingService = serviceLoader.findFirst()
        //     .orElseThrow(() -> new RuntimeException("SharedGreetingService not found"));
    }
    
    @Override
    public String chat() {
        String greeting = "sharedGreetingService.englishGreet()";
        return "Chat started! " + greeting + " How can I help you today?";
    }
}