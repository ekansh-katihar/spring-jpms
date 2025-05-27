package com.chat.controller;

import com.chat.internal.ChatService;
import com.chat.exception.ChatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * REST controller for chat endpoints
 */
@RestController
public class ChatController {
    
    private final ChatService chatService;
    
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @GetMapping("/chat")
    public ResponseEntity<String> chat() {
        try {
            String chatResponse = chatService.chat();
            return ResponseEntity.ok(chatResponse);
        } catch (Exception e) {
            throw new ChatException("Failed to initiate chat", e);
        }
    }
    
    /**
     * Exception advice for chat controller
     */
    @ControllerAdvice
    public static class ChatExceptionAdvice {
        
        @ExceptionHandler(ChatException.class)
        public ResponseEntity<String> handleChatException(ChatException e) {
            return ResponseEntity.internalServerError()
                .body("Chat Error: " + e.getMessage());
        }
        
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception e) {
            return ResponseEntity.internalServerError()
                .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}