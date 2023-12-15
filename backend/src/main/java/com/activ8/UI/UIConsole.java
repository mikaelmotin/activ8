package com.activ8.UI;

import com.activ8.model.*;
import com.activ8.payload.request.LoginRequest;
import com.activ8.payload.request.SignupRequest;
import com.activ8.service.*;
import com.activ8.view.FlashcardView;
import com.activ8.view.StudyFolderView;
import com.activ8.view.StudySessionView;
import com.activ8.view.StudySetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class UIConsole {
    private UserService userService;
    private StudyFolderService studyFolderService;
    private StudySetService studySetService;
    private FlashcardService flashcardService;
    private StudySessionService studySessionService;
    private String userId;
    private boolean inStudySession = false;
    private Flashcard currentFlashcard;
    private Scanner scanner = new Scanner(System.in);
    private FlashcardView flashcardView;
    private StudySetView studySetView;
    private StudyFolderView studyFolderView;
    private StudySessionView studySessionView;

    @Autowired
    public UIConsole(
            UserService userService,
            StudyFolderService studyFolderService,
            StudySetService studySetService,
            FlashcardService flashcardService,
            StudySessionService studySessionService,
            FlashcardView flashcardView,
            StudySetView studySetView,
            StudyFolderView studyFolderView,
            StudySessionView studySessionView
    ) {
        this.userService = userService;
        this.studyFolderService = studyFolderService;
        this.studySetService = studySetService;
        this.flashcardService = flashcardService;
        this.studySessionService = studySessionService;
        this.flashcardView = flashcardView;
        this.studySetView= studySetView;
        this.studyFolderView = studyFolderView;
        this.studySessionView = studySessionView;
    }


    // Methods to handle user interactions and menus
    public  void signUp() {
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

    public  void signIn() {
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
                studyFolderView.displayStudyFolderOptions();
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

    public  void chooseFolder() {
        studyFolderView.displayChooseFolderMessage();
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
            studyFolderView.displaySelectedFolder(selectedFolder);

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
    public  void createFolder() {
        System.out.println("\nCreate a new folder:");
        System.out.print("Enter folder name: ");
        String folderName = scanner.nextLine();

        StudyFolder newFolder = new StudyFolder(userId, folderName, "Description");
        studyFolderService.saveStudyFolder(newFolder);
        System.out.println("Folder created successfully!");
    }
    public  void removeFolder() {
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
    public  void chooseStudySet(StudyFolder folder) {
        studySetView.displayChooseStudySet(folder);
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
            studySetView.displaySelectedStudySet(selectedStudySet);

            if (!inStudySession) {
                // Display Study Set Options
                while (true) {
                    studySetView.displayStudySetActions();
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
                // Display Study Session Options
                while (true) {
                    studySessionView.displayStudySessionActions();
                    int studySessionActionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (studySessionActionChoice) {
                        case 1:
                            flipFlashcard(currentFlashcard);
                            break;
                        case 2:
                            nextFlashcard();
                            break;
                        case 3:
                            rateDifficulty(currentFlashcard);
                            break;
                        case 4:
                            endStudySession();
                            return; // Exit method to end the study session
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                }
            }
        } else {
            System.out.println("Invalid study set choice. Please select a valid study set.");
        }
    }
    public  void createStudySet(StudyFolder folder) {
        studySetView.displayCreateStudySetMessage();

        studySetView.displayEnterStudySetTitle();
        String title = scanner.nextLine();

        studySetView.displayEnterStudySetDescription();
        String description = scanner.nextLine();

        StudySet studySet = new StudySet(folder.userId(), folder.id(), title, description);
        studySetService.saveStudySet(studySet);
        studySetView.displaySuccessfulCreation();
    }
    public  void removeStudySet(StudyFolder folder) {
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
            studySetService.deleteStudySet(userId, selectedStudySet.id());
            System.out.println("Study Set removed successfully!");
        } else {
            System.out.println("Invalid study set choice. Please select a valid study set.");
        }
        return;
    }
    public void seeFlashcards(StudySet studySet) {
        List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet(studySet.id());
        if (!flashcards.isEmpty()) {
            while (true) {
                System.out.println("\nFlashcards in " + studySet.title() + ":");
                studySetView.displayAllFlashcards(studySet);

                flashcardView.displayFlashcardActions();
                int flashcardOption = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (flashcardOption == 1) {
                    removeFlashcard(studySet);
                } else if (flashcardOption == 2) {
                    return; // Back to the list of Flashcards
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } else {
            System.out.println("No flashcards found in " + studySet.title() + ".");
        }
    }
    public  void createFlashcard(StudySet studySet) {
        System.out.println("\nCreating a new Flashcard for " + studySet.title() + ":");

        System.out.print("Enter the Flashcard term: ");
        String term = scanner.nextLine();

        System.out.print("Enter the Flashcard definition: ");
        String definition = scanner.nextLine();

        SimpleFlashcard flashcard = new SimpleFlashcard(studySet.id(), term, definition, EDifficulty.MEDIUM);
        flashcardService.saveFlashcard(flashcard);
        System.out.println("Flashcard created successfully!");
    }

    public void removeFlashcard(StudySet studySet) {
        System.out.println("\nRemove Flashcard:");
        List<Flashcard> flashcardsInSet = flashcardService.getAllFlashcardsInStudySet(studySet.id());

        if (flashcardsInSet.isEmpty()) {
            System.out.println("No flashcards found in " + studySet.title() + ".");
            return;
        }

        studySetView.displayAllFlashcards(studySet);

        System.out.print("Enter the flashcard number to remove: ");
        int flashcardChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (flashcardChoice > 0 && flashcardChoice <= flashcardsInSet.size()) {
            Flashcard selectedFlashcard = flashcardsInSet.get(flashcardChoice - 1);
            flashcardService.deleteFlashcard(userId, selectedFlashcard.getId());
            System.out.println("Flashcard removed successfully!");
        } else {
            System.out.println("Invalid flashcard choice. Please select a valid flashcard.");
        }
    }

    public  void startStudySession(StudySet studySet) {
        inStudySession = true;
        studySessionService.startFreeRoamStudySession(userId, studySet.id());
        currentFlashcard = studySessionService.nextCard(userId);
        studySessionView.displayStudySessionStarting();
        flashcardView.displayFlashcardDefinition(currentFlashcard);

        // After displaying flashcard details, go directly to study session actions
        while (inStudySession) {
            studySessionView.displayStudySessionActions();
            int studySessionActionChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (studySessionActionChoice) {
                case 1:
                    flipFlashcard(currentFlashcard);
                    break;
                case 2:
                    currentFlashcard = studySessionService.nextCard(userId);
                    flashcardView.displayFlashcardDetails(currentFlashcard);
                    break;
                case 3:
                    rateDifficulty(currentFlashcard);
                    break;
                case 4:
                    endStudySession();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public  void nextFlashcard() {
        currentFlashcard = studySessionService.nextCard(userId);
        flashcardView.displayFlashcardDefinition(currentFlashcard);
    }
    public  void flipFlashcard(Flashcard flashcard) {
        if (flashcard.isTermOnDisplay) {
            flashcardView.displayFlashcardDefinition(flashcard);
            flashcard.setTermOnDisplay(false);
        } else {
            flashcardView.displayFlashcardTitle(flashcard);
            flashcard.setTermOnDisplay(true);
        }
    }
    public  void rateDifficulty(Flashcard flashcard) {
        studySessionView.displayRateFlashcardOptions();

        int difficultyChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        EDifficulty difficulty;
        switch (difficultyChoice) {
            case 1:
                difficulty = EDifficulty.EASY;
                break;
            case 2:
                difficulty = EDifficulty.MEDIUM;
                break;
            case 3:
                difficulty = EDifficulty.HARD;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Medium difficulty.");
                difficulty = EDifficulty.MEDIUM;
                break;
        }
        studySessionService.assignDifficultyToFlashcard(userId,flashcard.getId(), difficulty);
        studySessionView.displayAssignedDifficulty(difficulty);
    }
    public  void endStudySession(){
        inStudySession = false;
        studySessionService.endStudySession(userId);
        studySessionView.displayStudySessionEnded();
    }
}