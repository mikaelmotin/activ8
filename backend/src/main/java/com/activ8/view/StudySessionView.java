package com.activ8.view;

import com.activ8.model.EDifficulty;
import com.activ8.model.StudySession;
import com.activ8.model.StudySet;
import com.activ8.service.StudySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudySessionView {
    @Autowired
    StudySetService studySetService;
    public void displayStudySessionStarting(){
        System.out.println("Starting study session");
    }
    public void displayStudySessionEnded(){
        System.out.println("\nStudy Session ended.");
    }
    public void displayRateFlashcardOptions(){
        System.out.println("\nRate Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter your choice: ");
    }
    public void displayAssignedDifficulty(EDifficulty difficulty){System.out.println("Difficulty rated as: " + difficulty.toString());}

    public void displayStudySessionActions() {
        System.out.println("\nStudy Session Actions:");
        System.out.println("1. Flip Card");
        System.out.println("2. Next Card");
        System.out.println("3. Rate Difficulty");
        System.out.println("4. End Study Session");
        System.out.print("Enter your choice: ");
    }

}