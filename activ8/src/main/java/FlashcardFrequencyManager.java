import java.util.*;

public class FlashcardFrequencyManager implements FrequencyManager {
    private Map<Difficulty, List<FakeFlashcard>> flashcardsDifficultyMap;
    private Map<Difficulty, Integer> difficultyCounters;
    private List<Difficulty> difficultyProbabilities;

    private Difficulty lastGeneratedDifficulty;
    private FakeFlashcard lastGeneratedFlashcard;

    public FlashcardFrequencyManager() {
        this.flashcardsDifficultyMap = new HashMap<>();
        this.difficultyCounters = new HashMap<>();
        this.difficultyProbabilities = new ArrayList<>();

        difficultyProbabilities.add(Difficulty.HARD);
        difficultyProbabilities.add(Difficulty.MEDIUM);
        difficultyProbabilities.add(Difficulty.EASY);

        for (Difficulty difficulty : difficultyProbabilities) {
            difficultyCounters.put(difficulty, 0);
        }
    }

    @Override
    public void assignInitialDifficulty(Map<FakeFlashcard, Difficulty> flashcards) {
        for (FakeFlashcard flashcard : flashcards.keySet()) {
            assignDifficulty(flashcard, Difficulty.MEDIUM);
        }
    }

    @Override
    public Map<Difficulty, List<FakeFlashcard>> getFlashcardsDifficultyMap() {
        return flashcardsDifficultyMap;
    }

    @Override
    public Map<Difficulty, Integer> getDifficultyCounters() {
        return difficultyCounters;
    }

    @Override
    public List<Difficulty> getDifficultyProbabilities() {
        return difficultyProbabilities;
    }

    @Override
    public void assignDifficulty(FakeFlashcard flashcard, Difficulty newDifficulty) {
        // Get the current difficulty of the flashcard
        Difficulty currentDifficulty = getCurrentDifficulty(flashcard);

        // Remove the flashcard from the current difficulty level
        removeFromDifficulty(currentDifficulty, flashcard);

        // Add the flashcard to the new difficulty level
        addToDifficulty(newDifficulty, flashcard);
    }

    private Difficulty getCurrentDifficulty(FakeFlashcard flashcard) {
        for (Map.Entry<Difficulty, List<FakeFlashcard>> entry : flashcardsDifficultyMap.entrySet()) {
            if (entry.getValue().contains(flashcard)) {
                return entry.getKey();
            }
        }
        // Default to MEDIUM if not found (you can adjust this logic based on your requirements)
        return Difficulty.MEDIUM;
    }

    private void removeFromDifficulty(Difficulty difficulty, FakeFlashcard flashcard) {
        List<FakeFlashcard> flashcards = flashcardsDifficultyMap.get(difficulty);
        if (flashcards != null) {
            flashcards.remove(flashcard);
            difficultyCounters.put(difficulty, difficultyCounters.get(difficulty) - 1);
        }
    }

    private void addToDifficulty(Difficulty difficulty, FakeFlashcard flashcard) {
        List<FakeFlashcard> flashcards = flashcardsDifficultyMap.computeIfAbsent(difficulty, k -> new ArrayList<>());
        flashcards.add(flashcard);
        difficultyCounters.put(difficulty, difficultyCounters.get(difficulty) + 1);
    }

    private Difficulty selectRandomDifficulty() {
        Random random = new Random();
        double randomValue = random.nextDouble();
        // Adjust the probabilities based on your preferences
        if (randomValue < 0.6) {
            // 60% chance for Hard
            return Difficulty.HARD;
        } else if (randomValue < 0.9) {
            return Difficulty.MEDIUM;
        } else {
            // 10% chance for Easy
            return Difficulty.EASY;
        }
    }

    public FakeFlashcard generateNextCard(Map<Difficulty, List<FakeFlashcard>> flashcardsDifficultyMap) {
        Difficulty selectedDifficulty = selectRandomDifficulty();
        List<FakeFlashcard> flashcardsForDifficulty = flashcardsDifficultyMap.get(selectedDifficulty);

        if (flashcardsForDifficulty != null && !flashcardsForDifficulty.isEmpty()) {
            FakeFlashcard randomFlashcard;

            do {
                // Select a random index from the list
                int randomIndex = new Random().nextInt(flashcardsForDifficulty.size());

                // Get the random flashcard
                randomFlashcard = flashcardsForDifficulty.get(randomIndex);
            } while (randomFlashcard == lastGeneratedFlashcard);

            // Update the last generated flashcard
            lastGeneratedFlashcard = randomFlashcard;

            // Now you can work with 'randomFlashcard'...
            System.out.println("Generated next card with difficulty: " + selectedDifficulty + ", Flashcard: " + randomFlashcard.getQuestion());

            return randomFlashcard;
        }

        // Handle the case where no flashcards are found for the selected difficulty
        System.out.println("No flashcards found for the selected difficulty: " + selectedDifficulty);

        // Retry with a different difficulty
        return generateNextCard(flashcardsDifficultyMap);
    }

}
