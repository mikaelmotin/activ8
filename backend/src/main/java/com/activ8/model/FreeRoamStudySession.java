package com.activ8.model;

import com.activ8.eventbus.EventBus;
import com.activ8.service.FlashcardService;

import org.springframework.beans.factory.annotation.Autowired;


public class FreeRoamStudySession implements StudySession {


    private FlashcardService flashcardService;
    private String studySetId;
    private boolean isStarted;

    private FlashcardFrequencyManager flashcardFrequencyManager;


    public FreeRoamStudySession(String studySetId, FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
        this.studySetId = studySetId;
        this.flashcardFrequencyManager = new FlashcardFrequencyManager(studySetId, flashcardService, new RandomDifficultySelectionStrategy()); 
    }

    @Override
    public void start(String userId) {
        if (!isStarted) {
            isStarted = true;
        }
        // FlashcardfrequencyManager should already have been initialized

    }

    @Override
    public void end() {
        flashcardFrequencyManager = null;
    }

    @Override
    public Flashcard nextCard() {
        Flashcard nextFlashcard = flashcardFrequencyManager.generateNextCard(
            flashcardFrequencyManager.getFlashcardsDifficultyMap()
            );
        
        
        return nextFlashcard;
        // // Log event for study session progress
        // eventBus.publish(new StudySessionProgressEvent(this));

        // // Log event for flashcard flipped - not here tho
        // eventBus.publish(new FlashcardFlippedEvent(this));
    }

    public void assignDifficulty(Flashcard flashcard, EDifficulty difficulty) {
        flashcardFrequencyManager.assignDifficulty(flashcard, difficulty);
    }

}
