/*package com.activ8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.activ8.model.Flashcard;
import com.activ8.service.FlashcardService;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    FlashcardService flashcardService;

    @SendTo("/topic/nextCard")
    public String notifyFrontendNextCard(Flashcard flashcard) {
        // Convert the Flashcard to JSON or a suitable format
        return "Apaapapapa";
        //return flashcardService.convertFlashcardToJson(flashcard);
    }

    // A simple endpoint to trigger sending a message
    @GetMapping("/send-test-message")
    public void sendTestMessage() {
        String testMessage = "Hello from the server!";
        messagingTemplate.convertAndSend("/topic/nextCard", testMessage);
    }
}*/
