package com.activ8.view;
import com.activ8.model.Flashcard;
import com.activ8.model.StudyFolder;
import com.activ8.model.StudySet;
import com.activ8.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudySetView {
    @Autowired
    FlashcardService flashcardService;

    public void displayStudySetDetails(StudySet studySet) {
        System.out.println("Study Set Title: " + studySet.title());
        System.out.println("Study Set Description: " + studySet.description());
        System.out.println("-------------");
    }

    public void displayStudySetTitle(StudySet studySet) {
        System.out.println("Study Set Title: " + studySet.title());
    }

    public void displayStudySetDescription(StudySet studySet) {
        System.out.println("Study Set Description: " + studySet.description());
    }
    public void displayAllFlashcards(StudySet studySet) {
        List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet(studySet.id());

        if (flashcards.isEmpty()) {
            System.out.println("No flashcards found in " + studySet.title());
        } else {
            System.out.println("Flashcards in " + studySet.title() + ":");
            for (int i = 0; i < flashcards.size(); i++) {
                Flashcard flashcard = flashcards.get(i);
                System.out.println((i + 1) + ". Term: " + flashcard.getTerm() + ", Definition: " + flashcard.getDefinition());
            }
        }
    }
    public void displayStudySetOptions(){
        System.out.println("\nStudySet Options:");
        System.out.println("1. Choose Study Set");
        System.out.println("2. Create New Study Set");
        System.out.println("3. Remove Study Set");
        System.out.println("4. Exit StudySet");

        System.out.print("Enter your choice: ");
    }
    public void displayChooseStudySet(StudyFolder folder){System.out.println("\nChoose a Study Set in folder '" + folder.title() + "':");}
    public void displaySelectedStudySet(StudySet selectedStudySet){System.out.println("Selected Study Set: " + selectedStudySet.title());}

    public void displayStudySetActions(){
        System.out.println("\nStudy Set Actions:");
        System.out.println("1. Create Flashcard");
        System.out.println("2. Remove Flashcard");
        System.out.println("3. See Flashcards");
        System.out.println("4. Start Study Session");
        System.out.println("5. Back to Study Sets");
        System.out.print("Enter your choice: ");
    }
    public void displayCreateStudySetMessage(){
        System.out.println("\nCreating a new Study Set:");
    }
    public void displayEnterStudySetTitle(){
        System.out.print("Enter the Study Set title: ");
    }
    public void displayEnterStudySetDescription(){
        System.out.print("Enter the Study Set description: ");
    }
    public void displaySuccessfulCreation(){
        System.out.println("Study Set created successfully!");
    }
}