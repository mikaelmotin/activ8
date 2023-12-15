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

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbTerminalApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("APPLICATION STARTED");
        UIConsole uiConsole = new UIConsole(userService, studyFolderService, studySetService, flashcardService, studySessionService, flashcardView,studySetView,studyFolderView,studySessionView);

        // Subscribe StudySessionStartedSubscriber to EventBus
        eventBus.subscribe(studySessionStartedSubscriber);

        startTerminalProgram(uiConsole);
    }

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
