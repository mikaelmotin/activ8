package com.activ8.model;

import java.util.*;

public class FrequencyManagerImpl implements FrequencyManager{
    private final Map<EDifficulty, List<Flashcard>> flashcardsDifficultyMap = new HashMap<>();
    private final Map<EDifficulty, Integer> difficultyCounters = new HashMap<>();
    private List<EDifficulty> difficultyProbabilities = new ArrayList<>();
    private Flashcard lastGeneratedFlashcard;

    public FrequencyManagerImpl() {
    }

    @Override
    public void assignInitialDifficulty(Map<Flashcard, EDifficulty> flashcards) {
        for (Flashcard flashcard : flashcards.keySet()) {
            assignDifficulty(flashcard, EDifficulty.MEDIUM);
        }
    }

    @Override
    public void assignDifficulty(Flashcard flashcard, EDifficulty newDifficulty) {
        // Get the current difficulty of the flashcard
        EDifficulty currentDifficulty = getCurrentDifficulty(flashcard);

        // Remove the flashcard from the current difficulty level
        removeFromDifficulty(currentDifficulty, flashcard);

        // Add the flashcard to the new difficulty level
        addToDifficulty(newDifficulty, flashcard);
    }
    private EDifficulty getCurrentDifficulty(Flashcard flashcard) {
        for (Map.Entry<EDifficulty, List<Flashcard>> entry : flashcardsDifficultyMap.entrySet()) {
            if (entry.getValue().contains(flashcard)) {
                return entry.getKey();
            }
        }
        // Default to MEDIUM if not found (you can adjust this logic based on your requirements)
        return EDifficulty.MEDIUM;
    }
    private void addToDifficulty(EDifficulty difficulty, Flashcard flashcard) {
        List<Flashcard> flashcards = new ArrayList<>(flashcardsDifficultyMap.getOrDefault(difficulty, new ArrayList<>()));
        flashcards.add(flashcard);
        flashcardsDifficultyMap.put(difficulty, flashcards);
        difficultyCounters.put(difficulty, difficultyCounters.getOrDefault(difficulty, 0) + 1);
    }
    private void removeFromDifficulty(EDifficulty difficulty, Flashcard flashcard) {
        List<Flashcard> flashcards = flashcardsDifficultyMap.getOrDefault(difficulty, new ArrayList<>());
        if (!flashcards.isEmpty()) {
            flashcards.removeIf(card -> card.equals(flashcard)); // Remove the flashcard using removeIf
            flashcardsDifficultyMap.put(difficulty, flashcards); // Update the map with the modified list
            difficultyCounters.put(difficulty, difficultyCounters.getOrDefault(difficulty, 0) - 1); // Update counter
        }
    }

    @Override
    public Map<EDifficulty, List<Flashcard>> getFlashcardsDifficultyMap() {
        return flashcardsDifficultyMap;
    }

    @Override
    public Map<EDifficulty, Integer> getDifficultyCounters() {
        return difficultyCounters;
    }

    @Override
    public List<EDifficulty> getDifficultyProbabilities() {
        return difficultyProbabilities;
    }
    public Flashcard generateNextCard(Map<EDifficulty, List<Flashcard>> flashcardsDifficultyMap) {
        EDifficulty selectedDifficulty = selectRandomDifficulty();
        List<Flashcard> flashcardsForDifficulty = flashcardsDifficultyMap.get(selectedDifficulty);

        if (flashcardsForDifficulty != null && !flashcardsForDifficulty.isEmpty()) {
            Flashcard randomFlashcard;

            do {
                // Select a random index from the list
                int randomIndex = new Random().nextInt(flashcardsForDifficulty.size());

                // Get the random flashcard
                randomFlashcard = flashcardsForDifficulty.get(randomIndex);
            } while (randomFlashcard == lastGeneratedFlashcard);

            // Update the last generated flashcard
            lastGeneratedFlashcard = randomFlashcard;

            // Now you can work with 'randomFlashcard'...
            System.out.println("Generated next card with difficulty: " + selectedDifficulty + ", Flashcard: " + randomFlashcard.getDefinition());

            return randomFlashcard;
        }

        // Handle the case where no flashcards are found for the selected difficulty
        System.out.println("No flashcards found for the selected difficulty: " + selectedDifficulty);

        // Retry with a different difficulty
        return generateNextCard(flashcardsDifficultyMap);
    }
    private EDifficulty selectRandomDifficulty() {
        Random random = new Random();
        double randomValue = random.nextDouble();
        // Adjust the probabilities based on your preferences
        if (randomValue < 0.6) {
            // 60% chance for Hard
            return EDifficulty.HARD;
        } else if (randomValue < 0.9) {
            return EDifficulty.MEDIUM;
        } else {
            // 10% chance for Easy
            return EDifficulty.EASY;
        }
    }
}
