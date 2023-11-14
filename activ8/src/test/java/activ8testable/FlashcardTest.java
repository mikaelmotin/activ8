package activ8testable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FlashcardTest {
    //Creates a Flashcard.
    Flashcard testCard = new Flashcard("test", "A card for testing");

    //Tests that the created test card is created with the correct term and definition.
    @Test
    void FlashcardConstructor() {
        assertEquals(testCard.getTerm(),"test");
        assertEquals(testCard.getDefinition(),"A card for testing");
    }
    //Tests that the method getTerm() returns the correct term.
    @Test
    void getTerm() {
        String term = testCard.getTerm();
        assertEquals(term, "test");
    }

    //Tests that the method getDefinition() returns the correct definition.
    @Test
    void getDefinition() {
        String definition = testCard.getDefinition();
        assertEquals(definition, "A card for testing");
    }

    //Tests that the method setTerm() edits a Flashcards term correctly.
    @Test
    void setTerm() {
        testCard.setTerm("tested");
        String newTerm = testCard.getTerm();
        assertEquals(newTerm,"tested");
    }

    //Tests that the method setDefinition() edits a Flashcards definition correctly.
    @Test
    void setDefinition() {
        testCard.setDefinition("A tested definition");
        String newDefinition = testCard.getDefinition();
        assertEquals(newDefinition,"A tested definition");
    }
}