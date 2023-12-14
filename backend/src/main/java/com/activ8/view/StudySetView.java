package com.activ8.view;
import com.activ8.model.Flashcard;
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
}