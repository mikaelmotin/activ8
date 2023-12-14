package com.activ8;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.subscribers.StudySessionStartedEventSubscriber;

import com.activ8.model.*;
import com.activ8.payload.request.SignupRequest;
import com.activ8.service.*;
import com.activ8.payload.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan({"com.activ8", "com.activ8.eventbus.subscribers"})
public class SpringBootMongodbLoginApplication implements CommandLineRunner {

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
    private String userId;
    private boolean inStudySession = false;
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbLoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("APPLICATION STARTED");

        // Subscribe StudySessionStartedSubscriber to EventBus
        eventBus.subscribe(studySessionStartedSubscriber);

        System.out.println("Welcome to the Activ8 Terminal Program!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    System.out.println("Exiting Terminal Program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void signIn() {
        System.out.println("\nSign In:");
        System.out.print("Enter username: ");
        String signInUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String signInPassword = scanner.nextLine();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(signInUsername);
        loginRequest.setPassword(signInPassword);

        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest);
        if (authenticatedUser.isPresent()) {
            System.out.println("Sign In Successful!");
            userId = authenticatedUser.get().getId();

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Choose a folder");
                System.out.println("2. Add a new folder");
                System.out.println("3. Remove a folder");
                System.out.println("4. Sign Out");

                System.out.print("Enter your choice: ");
                int folderOption = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (folderOption) {
                    case 1:
                        chooseFolder();
                        break;
                    case 2:
                        createFolder();
                        break;
                    case 3:
                        removeFolder();
                        break;
                    case 4:
                        System.out.println("Signing out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        } else {
            System.out.println("Sign In Failed. Invalid credentials.");
        }
    }

    private void signUp() {
        System.out.println("\nSign Up:");
        System.out.print("Enter username: ");
        String signUpUsername = scanner.nextLine();

        System.out.print("Enter email: ");
        String signUpEmail = scanner.nextLine();

        System.out.print("Enter password: ");
        String signUpPassword = scanner.nextLine();

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername(signUpUsername);
        signupRequest.setEmail(signUpEmail);
        signupRequest.setPassword(signUpPassword);

        userService.registerUser(signupRequest);
        System.out.println("Sign Up Successful!");
    }

    private void chooseFolder() {
        System.out.println("\nChoose a folder:");
        // Assuming this fetches folders associated with the logged-in user
        List<StudyFolder> userFolders = studyFolderService.getAllStudyFolders(userId);

        int index = 1;
        for (StudyFolder folder : userFolders) {
            System.out.println(index + ". " + folder.title());
            index++;
        }

        System.out.print("Enter the folder number to select: ");
        int folderChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (folderChoice > 0 && folderChoice <= userFolders.size()) {
            StudyFolder selectedFolder = userFolders.get(folderChoice - 1);
            System.out.println("Selected Folder: " + selectedFolder.title());

            while (true) {
                System.out.println("\nStudySet Options:");
                System.out.println("1. Choose Study Set");
                System.out.println("2. Create New Study Set");
                System.out.println("3. Remove Study Set");
                System.out.println("4. Exit StudySet");

                System.out.print("Enter your choice: ");
                int folderOption = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (folderOption) {
                    case 1:
                        chooseStudySet(selectedFolder);
                        break;
                    case 2:
                        createStudySet(selectedFolder);
                        break;
                    case 3:
                        removeStudySet(selectedFolder);
                        break;
                    case 4:
                        System.out.println("Exiting Folder Options.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid folder choice. Please select a valid folder.");
        }
    }
    private void createFolder() {
        System.out.println("\nCreate a new folder:");
        System.out.print("Enter folder name: ");
        String folderName = scanner.nextLine();

        StudyFolder newFolder = new StudyFolder(userId, folderName, "Description");
        studyFolderService.saveStudyFolder(newFolder);
        System.out.println("Folder created successfully!");
    }
    private void removeFolder() {
        while (true) {
            System.out.println("\nRemove a folder:");
            List<StudyFolder> userFolders = studyFolderService.getAllStudyFolders(userId);

            if (userFolders.isEmpty()) {
                System.out.println("No folders found to remove.");
                return;
            }

            int index = 1;
            for (StudyFolder folder : userFolders) {
                System.out.println(index + ". " + folder.title());
                index++;
            }

            System.out.println("0. Cancel");
            System.out.print("Enter the number of the folder to remove (0 to cancel): ");
            int folderChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (folderChoice == 0) {
                System.out.println("Operation cancelled.");
                return;
            }

            if (folderChoice > 0 && folderChoice <= userFolders.size()) {
                StudyFolder selectedFolder = userFolders.get(folderChoice - 1);
                String folderIdToRemove = selectedFolder.id();

                // Check if the folder has study sets inside before removing
                List<StudySet> studySetsInFolder = studySetService.getAllStudySets(folderIdToRemove);
                if (!studySetsInFolder.isEmpty()) {
                    System.out.println("The folder has study sets. Please remove study sets before removing the folder.");
                } else {
                    // Remove the folder
                    studyFolderService.deleteStudyFolder(userId, folderIdToRemove);
                    System.out.println("Folder removed successfully.");
                    return;
                }
            } else {
                System.out.println("Invalid folder choice. Please select a valid folder or enter 0 to cancel.");
            }
        }
    }
    private void chooseStudySet(StudyFolder folder) {
        System.out.println("\nChoose a Study Set in folder '" + folder.title() + "':");
        List<StudySet> studySetsInFolder = studySetService.getAllStudySets(folder.id());

        int index = 1;
        for (StudySet studySet : studySetsInFolder) {
            System.out.println(index + ". " + studySet.title());
            index++;
        }

        System.out.print("Enter the study set number to select: ");
        int studySetChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (studySetChoice > 0 && studySetChoice <= studySetsInFolder.size()) {
            StudySet selectedStudySet = studySetsInFolder.get(studySetChoice - 1);
            System.out.println("Selected Study Set: " + selectedStudySet.title());

            if (!inStudySession) {
                while (true) {
                    System.out.println("\nStudy Set Actions:");
                    System.out.println("1. Create Flashcard");
                    System.out.println("2. Remove Flashcard");
                    System.out.println("3. See Flashcards");
                    System.out.println("4. Start Study Session");
                    System.out.println("5. Back to Study Sets");
                    System.out.print("Enter your choice: ");
                    int studySetActionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (studySetActionChoice) {
                        case 1:
                            createFlashcard(selectedStudySet);
                            break;
                        case 2:
                            removeFlashcard(selectedStudySet);
                            break;
                        case 3:
                            seeFlashcards(selectedStudySet);
                            break;
                        case 4:
                            startStudySession(selectedStudySet);
                            return; // Exit method to start the study session
                        case 5:
                            return; // Back to the list of Study Sets
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                }
            } else {
                startStudySession(selectedStudySet);
            }
        } else {
            System.out.println("Invalid study set choice. Please select a valid study set.");
        }
    }
    private void seeFlashcards(StudySet studySet) {
        List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet(studySet.id());
        if (!flashcards.isEmpty()) {
            System.out.println("\nFlashcards in " + studySet.title() + ":");
            int index = 1;
            for (Flashcard flashcard : flashcards) {
                System.out.println(index + ". Term: " + flashcard.getTerm() + ", Definition: " + flashcard.getDefinition());
                index++;
            }
        } else {
            System.out.println("No flashcards found in " + studySet.title() + ".");
        }
    }
    private void createStudySet(StudyFolder folder) {
        System.out.println("\nCreating a new Study Set:");

        System.out.print("Enter the Study Set title: ");
        String title = scanner.nextLine();

        System.out.print("Enter the Study Set description: ");
        String description = scanner.nextLine();

        StudySet studySet = new StudySet(folder.userId(), folder.id(), title, description);
        studySetService.saveStudySet(studySet);
        System.out.println("Study Set created successfully!");
    }
    private void removeStudySet(StudyFolder folder) {
        System.out.println("\nRemove Study Set:");
        // Fetch and display existing study sets in the chosen folder
        List<StudySet> studySetsInFolder = studySetService.getAllStudySets(folder.id());

        int index = 1;
        for (StudySet set : studySetsInFolder) {
            System.out.println(index + ". " + set.title());
            index++;
        }

        System.out.print("Enter the study set number to remove: ");
        int setChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (setChoice > 0 && setChoice <= studySetsInFolder.size()) {
            StudySet selectedStudySet = studySetsInFolder.get(setChoice - 1);
            studySetService.deleteStudySet(folder.userId(), selectedStudySet.id());
            System.out.println("Study Set removed successfully!");
        } else {
            System.out.println("Invalid study set choice. Please select a valid study set.");
        }
    }
    private void chooseStudySetActions(StudySet studySet) {
        System.out.println("\nChoose an action for the selected Study Set:");
        System.out.println("1. Create Flashcard");
        System.out.println("2. Remove Flashcard");
        System.out.println("3. Start Study Session");
        System.out.println("4. Back to Study Sets");
        System.out.print("Enter your choice: ");
        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (actionChoice) {
            case 1:
                createFlashcard(studySet);
                break;
            case 2:
                removeFlashcard(studySet);
                break;
            case 3:
                startStudySession(studySet);
                break;
            case 4:
                // Return to the list of Study Sets
                chooseStudySet(studyFolderService.getStudyFolder(studySet.studyFolderId()).get());
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
    private void createFlashcard(StudySet studySet) {
        System.out.println("\nCreating a new Flashcard for " + studySet.title() + ":");

        System.out.print("Enter the Flashcard term: ");
        String term = scanner.nextLine();

        System.out.print("Enter the Flashcard definition: ");
        String definition = scanner.nextLine();

        SimpleFlashcard flashcard = new SimpleFlashcard(studySet.id(), term, definition, EDifficulty.MEDIUM);
        flashcardService.saveFlashcard(flashcard);
        System.out.println("Flashcard created successfully!");
    }

    private void removeFlashcard(StudySet studySet) {
        System.out.println("\nRemove Flashcard:");
        // Fetch and display existing flashcards in the chosen study set
        List<Flashcard> flashcardsInSet = flashcardService.getAllFlashcardsInStudySet(studySet.id());

        int index = 1;
        for (Flashcard flashcard : flashcardsInSet) {
            System.out.println(index + ". " + flashcard.getTerm());
            index++;
        }

        System.out.print("Enter the flashcard number to remove: ");
        int flashcardChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (flashcardChoice > 0 && flashcardChoice <= flashcardsInSet.size()) {
            Flashcard selectedFlashcard = flashcardsInSet.get(flashcardChoice - 1);
            flashcardService.deleteFlashcard(studySet.id(), selectedFlashcard.getId());
            System.out.println("Flashcard removed successfully!");
        } else {
            System.out.println("Invalid flashcard choice. Please select a valid flashcard.");
        }
    }

    private void startStudySession(StudySet studySet) {
        // Logic to start a study session using the chosen study set
        // You can implement the functionality to initiate a study session here
        inStudySession = true;
        studySessionService.startFreeRoamStudySession(userId, studySet.id());
        studySessionService.nextCard(userId);
        System.out.println("Starting study session for " + studySet.title() + "...");
    }


}

