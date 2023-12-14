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
}
