package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class StudySessionProgress {
    private int nFlashcardsFlipped;

    private int nFlashcardsIterated;

    private boolean completed;

    // Map to store the count of flips for each flashcard
    private ConcurrentHashMap<String, Integer> individualFlashcardsFlipCount;

    public StudySessionProgress() {
        this.nFlashcardsFlipped = 0;
        this.nFlashcardsIterated = 0;
        this.completed = false;
        this.individualFlashcardsFlipCount = new ConcurrentHashMap<>();
    }


    public int getCardFlips() {
        return nFlashcardsFlipped;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getnFlashcardsIterated() {
        return nFlashcardsIterated;
    }


    /**
     * Records that a flashcard has been iterated (=User switched to next card).
     */
    public void recordFlashcardIterated() {
        nFlashcardsIterated++;
    }

    /**
     * Records a flip of a flashcard.
     * 
     * @param flashcardId The ID of the flashcard that was flipped.
     */
    public void recordFlashcardFlip(String flashcardId) {
        individualFlashcardsFlipCount.put(flashcardId, individualFlashcardsFlipCount.getOrDefault(flashcardId, 0) + 1);
    }

    /**
     * Retrieves the number of times a specific flashcard has been flipped.
     * 
     * @param flashcardId The ID of the flashcard.
     * @return The number of times the flashcard has been flipped.
     */
    public int getFlipCount(String flashcardId) {
        return individualFlashcardsFlipCount.getOrDefault(flashcardId, 0);
    }

    /**
     * Gets the entire map of flashcard flips.
     * 
     * @return A map with flashcard ID as key and flip count as value.
     */
    public ConcurrentHashMap<String, Integer> getAllFlipCounts() {
        return new ConcurrentHashMap<>(individualFlashcardsFlipCount);
    }

    /**
     * Gets the total number of flips across all flashcards.
     * 
     * @return The total number of flips.
     */
    public int getTotalFlips() {
        int totalFlips = 0;
        for (int flips : individualFlashcardsFlipCount.values()) {
            totalFlips += flips;
        }
        return totalFlips;
    }
}
