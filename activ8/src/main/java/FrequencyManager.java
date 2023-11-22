import java.util.List;
import java.util.Map;

public interface FrequencyManager {
    void assignInitialDifficulty(Map<FakeFlashcard, Difficulty> flashcards);

    void assignDifficulty(FakeFlashcard flashcard, Difficulty difficulty);

    Map<Difficulty, List<FakeFlashcard>> getFlashcardsDifficultyMap();

    Map<Difficulty, Integer> getDifficultyCounters();

    List<Difficulty> getDifficultyProbabilities();


}
