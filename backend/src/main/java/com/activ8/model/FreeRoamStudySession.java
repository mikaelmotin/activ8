package com.activ8.model;

import com.activ8.service.FlashcardService;

/**
 * FreeRoamStudySession represents a free-roaming study session where the user can explore
 * flashcards at their own pace. It uses the FlashcardFrequencyManager to manage flashcard
 * difficulties and distribution within a study set. This session allows users to start, end, and
 * retrieve the next flashcard based on difficulty.
 * 
 */
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
    }

    public void assignDifficulty(Flashcard flashcard, EDifficulty difficulty) {
        flashcardFrequencyManager.assignDifficulty(flashcard, difficulty);
    }

}
