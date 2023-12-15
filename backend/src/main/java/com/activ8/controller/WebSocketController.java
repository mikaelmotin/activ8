package com.activ8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.activ8.service.FlashcardService;

/**
 * WebSocket controller handling WebSocket communication for real-time updates.
 * This controller is responsible for processing progress start messages and sending
 * notifications to the subscribed clients.
 *
 * Endpoints:
 * - WebSocket message handling: /ws/start
 * - WebSocket topic for progress updates: /topic/progress
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    FlashcardService flashcardService;

    @MessageMapping("/start")
    @SendTo("/topic/progress")
    public String processProgressStart(String message) {
        // This method could be triggered when a new study session starts
        return "Session Started: " + message;
    }
}
