package com.activ8.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    /**
     * Generates a client sessionId  
     * @return String sessionId
     */
    public String generateSessionId() {
        String sessionId = UUID.randomUUID().toString();
        return sessionId;
    }

    public void notifyProgressBar(String sessionId, double progress) {
        System.out.println();
        System.out.println("SENDING THE PROGRESS UPDATE");
        System.out.println();
        messagingTemplate.convertAndSend("/topic/progress/" + sessionId, progress);
    }
    
}
