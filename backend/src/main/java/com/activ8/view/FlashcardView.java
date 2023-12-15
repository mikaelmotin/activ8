package com.activ8.view;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import org.springframework.stereotype.Component;

@Component
public class FlashcardView {


    public void displayFlashcardDetails(Flashcard flashcard) {
        System.out.println("Flashcard Term: " + flashcard.getTerm());
        System.out.println("Flashcard Definition: " + flashcard.getDefinition());
        System.out.println("-------------");
    }

    public void displayFlashcardTitle(Flashcard flashcard) {
        System.out.println("Flashcard Term: " + flashcard.getTerm());
    }

    public void displayFlashcardDefinition(Flashcard flashcard) {
        System.out.println("Flashcard Definition: " + flashcard.getDefinition());
    }
    public void displayFlashcardOptions(){
        System.out.println("\nFlashcard Options:");
        System.out.println("1. Remove Flashcard");
        System.out.println("2. Back to Study Set");
        System.out.print("Enter your choice: ");
    }
    public void displayFlashcardActions(){
        System.out.println("\nFlashcard Options:");
        System.out.println("1. Remove Flashcard");
        System.out.println("2. Back to Study Set");
        System.out.print("Enter your choice: ");
    }

}
