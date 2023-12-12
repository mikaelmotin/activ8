package com.activ8.model;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.FlashcardFlippedEvent;
import com.activ8.eventbus.events.StudySessionCompletedEvent;
import com.activ8.eventbus.events.StudySessionProgressEvent;
import com.activ8.eventbus.events.StudySessionStartedEvent;
import com.activ8.service.FlashcardService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class FreeRoamStudySession implements StudySession {

    @Autowired
    private EventBus eventBus;

    private String studySetId;
    private boolean isStarted;
    private FlashcardService flashcardService;
    private FlashcardFrequencyManager flashcardFrequencyManager;
    private SimpMessagingTemplate messagingTemplate;

    public FreeRoamStudySession(String studySetId) {
        this.studySetId = studySetId;
        this.flashcardFrequencyManager = new FlashcardFrequencyManager(null); //Skapa med FlashcardsService?
    }

    @Override
    public void start(String userId) {
        if (!isStarted) {
            isStarted = true;
            eventBus.publish(new StudySessionStartedEvent(this, userId, studySetId, LocalDateTime.now()));
        }
        // Logic
    }

    @Override
    public void end() {
        // Cleanup logic
        // Log event for study session completion
        //eventBus.publish(new StudySessionCompletedEvent(this));

    }

    @Override
    public void nextCard() {
        // Logic to retrieve and display the next card
        Flashcard nextFlashcard = flashcardFrequencyManager.generateNextCard(null);

        // Notify frontend about the next card
        notifyFrontendNextCard(nextFlashcard);

        // Log event for study session progress
        eventBus.publish(new StudySessionProgressEvent(this));

        // Log event for flashcard flipped - not here tho
        eventBus.publish(new FlashcardFlippedEvent(this));
    }


    private void notifyFrontendNextCard(Flashcard flashcard) {
        // Logic to send a real-time signal to the frontend about the next card
        
        String message = flashcardService.convertFlashcardToJson(flashcard);
        messagingTemplate.convertAndSend("/topic/nextCard", message);

    }
}
