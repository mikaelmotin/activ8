package com.activ8.model;
import java.util.List;
import java.util.Map;

public interface FrequencyManager {
    void assignInitialDifficulty(Map<Flashcard, EDifficulty> flashcards);

    void assignDifficulty(Flashcard flashcard, EDifficulty difficulty);

    Map<EDifficulty, List<Flashcard>> getFlashcardsDifficultyMap();

    Map<EDifficulty, Integer> getDifficultyCounters();

    List<EDifficulty> getDifficultyProbabilities();


}