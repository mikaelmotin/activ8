package com.activ8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.activ8.model.Flashcard;
import com.activ8.service.FlashcardService;

@Controller
public class WebSocketController {

    @Autowired
    FlashcardService flashcardService;

    @SendTo("/topic/nextCard")
    public String notifyFrontendNextCard(Flashcard flashcard) {
        // Convert the Flashcard to JSON or a suitable format
        return "Apaapapapa";
        //return flashcardService.convertFlashcardToJson(flashcard);
    }

    // Conversion method as before
}
