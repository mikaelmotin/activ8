package com.activ8.model;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencySimulator{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            FrequencyManagerImpl frequencyManager = new FrequencyManagerImpl();

            // Create flashcards for the simulation
            Flashcard flashcard1 = new SimpleFlashcard("1", "Question 1", "Answer 1", EDifficulty.MEDIUM);
            Flashcard flashcard2 = new SimpleFlashcard("2", "Question 2", "Answer 2", EDifficulty.EASY);
            Flashcard flashcard3 = new SimpleFlashcard("3", "Question 3", "Answer 3", EDifficulty.HARD);

            // Create a map of flashcards to assign initial difficulties
            Map<Flashcard, EDifficulty> flashcardsToAssign = new HashMap<>();
            flashcardsToAssign.put(flashcard1, EDifficulty.MEDIUM);
            flashcardsToAssign.put(flashcard2, EDifficulty.EASY);
            flashcardsToAssign.put(flashcard3, EDifficulty.HARD);

            // Assign initial difficulties
            frequencyManager.assignInitialDifficulty(flashcardsToAssign);

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

                    // Assign new difficulty to the generated flashcard
                    frequencyManager.assignDifficulty(nextFlashcard, newDifficulty);
                } else {
                    System.out.println("No more flashcards available. Exiting simulation.");
                    break; // Exit the loop if no more flashcards are available
                }
            }

            // Ask if the user wants to run the simulation again
            System.out.println("Do you want to run the simulation again? (yes/no): ");
            String input = scanner.nextLine().toLowerCase();
            if (!input.equals("yes")) {
                keepRunning = false;
            }
        }

        System.out.println("Simulation ended. Goodbye!");
    }
}