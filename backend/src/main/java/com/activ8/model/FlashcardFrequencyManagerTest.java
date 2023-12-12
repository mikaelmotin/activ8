package com.activ8.model;
import com.activ8.repository.FlashcardRepository;
import com.activ8.service.FlashcardService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class FlashcardFrequencyManagerTest {
    public void runSimulation() {
        FlashcardService flashcardServiceMock = Mockito.mock(FlashcardService.class);

        FlashcardFrequencyManager frequencyManager = new FlashcardFrequencyManager(flashcardServiceMock);


        // Create a few flashcards
        Flashcard earthRoundFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard mathFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard historyFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard scienceFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard literatureFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard geographyFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        Flashcard programmingFlashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);

        // Create a map of flashcards to assign initial difficulties
        Map<Flashcard, EDifficulty> flashcardsToAssign = new HashMap<>();
        flashcardsToAssign.put(earthRoundFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(mathFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(historyFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(scienceFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(literatureFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(geographyFlashcard, EDifficulty.MEDIUM);
        flashcardsToAssign.put(programmingFlashcard, EDifficulty.MEDIUM);

        // Assign initial difficulties
        frequencyManager.assignInitialDifficulty(flashcardsToAssign);

        // Print the contents of flashcardsDifficultyMap
        System.out.println("Flashcards Difficulty Map after initial assignment:");
        for (Map.Entry<EDifficulty, List<Flashcard>> entry : frequencyManager.getFlashcardsDifficultyMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Use a Scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Continuous loop to generate next flashcard until stopped
        while (true) {
            // Generate next flashcard
            Flashcard nextFlashcard = frequencyManager.generateNextCard(frequencyManager.getFlashcardsDifficultyMap());

            if (nextFlashcard != null) {
                System.out.println("Next flashcard: " + nextFlashcard.getTerm());

                // Prompt user for a new difficulty assignment
                EDifficulty newDifficulty = null;
                boolean validInput = false;

                while (!validInput) {
                    System.out.println("Enter difficulty for \"" + nextFlashcard.getTerm() + "\": ");
                    String input = scanner.nextLine().toUpperCase();

                    try {
                        newDifficulty = EDifficulty.valueOf(input);
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
    public static void main(String[] args) {
        FlashcardFrequencyManagerTest managerTest = new FlashcardFrequencyManagerTest();
        managerTest.runSimulation();
    }
}
