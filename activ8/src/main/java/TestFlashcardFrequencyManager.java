import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestFlashcardFrequencyManager {
    public static void main(String[] args) {
        FlashcardFrequencyManager frequencyManager = new FlashcardFrequencyManager();

        // Create a few flashcards
        FakeFlashcard earthRoundFlashcard = new FakeFlashcard("Why is the earth round", "Because of gravity");
        FakeFlashcard mathFlashcard = new FakeFlashcard("What is 2+2", "4");
        FakeFlashcard historyFlashcard = new FakeFlashcard("Who was the first president of the USA", "George Washington");
        FakeFlashcard scienceFlashcard = new FakeFlashcard("What is the chemical symbol for water", "H2O");
        FakeFlashcard literatureFlashcard = new FakeFlashcard("Who wrote Romeo and Juliet", "William Shakespeare");
        FakeFlashcard geographyFlashcard = new FakeFlashcard("What is the capital of France", "Paris");
        FakeFlashcard programmingFlashcard = new FakeFlashcard("What does HTML stand for", "Hypertext Markup Language");

        // Create a map of flashcards to assign initial difficulties
        Map<FakeFlashcard, Difficulty> flashcardsToAssign = new HashMap<>();
        flashcardsToAssign.put(earthRoundFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(mathFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(historyFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(scienceFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(literatureFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(geographyFlashcard, Difficulty.MEDIUM);
        flashcardsToAssign.put(programmingFlashcard, Difficulty.MEDIUM);

        // Assign initial difficulties
        frequencyManager.assignInitialDifficulty(flashcardsToAssign);

        // Print the contents of flashcardsDifficultyMap
        System.out.println("Flashcards Difficulty Map after initial assignment:");
        for (Map.Entry<Difficulty, List<FakeFlashcard>> entry : frequencyManager.getFlashcardsDifficultyMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Use a Scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Continuous loop to generate next flashcard until stopped
        while (true) {
            // Generate next flashcard
            FakeFlashcard nextFlashcard = frequencyManager.generateNextCard(frequencyManager.getFlashcardsDifficultyMap());

            if (nextFlashcard != null) {
                System.out.println("Next flashcard: " + nextFlashcard.getQuestion());

                // Prompt user for a new difficulty assignment
                Difficulty newDifficulty = null;
                boolean validInput = false;

                while (!validInput) {
                    System.out.println("Enter difficulty for \"" + nextFlashcard.getQuestion() + "\": ");
                    String input = scanner.nextLine().toUpperCase();

                    try {
                        newDifficulty = Difficulty.valueOf(input);
                        validInput = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid difficulty. Please enter 'EASY', 'MEDIUM', or 'HARD'.");
                    }
                }

                // Consume the newline character
                scanner.nextLine();

                // Assign new difficulty to the generated flashcard
                frequencyManager.assignDifficulty(nextFlashcard, newDifficulty);
            } else {
                System.out.println("No more flashcards available. Exiting.");
                break; // Exit the loop if no more flashcards are available
            }
        }
    }
}
