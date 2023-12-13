package com.activ8.model;

public interface StudySession {
    void start(String studySetId);
    void end();
    Flashcard nextCard();

    // Other methods related to the study session
    void assignDifficulty(Flashcard flashcard, EDifficulty difficulty);
}

