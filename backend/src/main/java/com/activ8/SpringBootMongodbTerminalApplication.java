/**
 * Entry point for the Spring Boot MongoDB Terminal Application.
 * This class initializes and starts the application with the terminal user interface.
 */
package com.activ8;

import com.activ8.UI.UIConsole;
import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.subscribers.StudySessionStartedEventSubscriber;
import com.activ8.service.*;
import com.activ8.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan({"com.activ8", "com.activ8.eventbus.subscribers"})
public class SpringBootMongodbTerminalApplication implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private StudySessionStartedEventSubscriber studySessionStartedSubscriber;
    @Autowired
    private UserService userService;
    @Autowired
    private StudyFolderService studyFolderService;
    @Autowired
    private StudySetService studySetService;
    @Autowired
    private FlashcardService flashcardService;
    @Autowired
    private StudySessionService studySessionService;
    @Autowired
    private FlashcardView flashcardView;
    @Autowired
    private StudyFolderView studyFolderView;
    @Autowired
    private StudySetView studySetView;
    @Autowired
    private StudySessionView studySessionView;

    /**
     * Main method to start the Spring Boot MongoDB Terminal Application.
     * Initializes the application context and starts the command-line runner.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbTerminalApplication.class, args);
    }

    /**
     * Runs the application by initializing the UIConsole and handling user interactions through the console interface.
     *
     * @param args Command-line arguments
     */
    @Override
    public void run(String... args) {
        System.out.println("APPLICATION STARTED");
        UIConsole uiConsole = new UIConsole(userService, studyFolderService, studySetService, flashcardService, studySessionService, flashcardView,studySetView,studyFolderView,studySessionView);

        // Subscribe StudySessionStartedSubscriber to EventBus
        eventBus.subscribe(studySessionStartedSubscriber);

        startTerminalProgram(uiConsole);
    }

    /**
     * Starts the terminal program, displays the welcome message, and handles user choices through the console interface.
     *
     * @param uiConsole The UIConsole for user interaction
     */
    private void startTerminalProgram(UIConsole uiConsole) {
        Scanner scanner = new Scanner(System.in);
        StartProgramView startProgramView = new StartProgramView(uiConsole);
        startProgramView.displayWelcomeMessage();

        while (true) {
            startProgramView.displayMenuAndGetChoice();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        uiConsole.signUp();
                        break;
                    case 2:
                        uiConsole.signIn();
                        break;
                    case 3:
                        startProgramView.displayExitMessage();
                        scanner.close();
                        return;
                    default:
                        startProgramView.displayInvalidChoiceMessage();
                        break;
                }
            } catch (InputMismatchException e) {
                // Handle the exception (e.g., inform the user, clear the scanner, etc.)
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }
}