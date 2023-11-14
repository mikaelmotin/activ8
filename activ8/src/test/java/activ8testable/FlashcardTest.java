package activ8testable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {
    Flashcard testCard = new Flashcard("test", "A card for testing");
    @Test
    void getTerm() {
        String term = testCard.getTerm();
        assertEquals(term, "test");
    }

    @Test
    void getDefinition() {
        String definition = testCard.getDefinition();
        assertEquals(definition, "A card for testing");
    }

    @Test
    void setTerm() {
        testCard.setTerm("tested");
        String newTerm = testCard.getTerm();
        assertEquals(newTerm,"tested");
    }

    @Test
    void setDefinition() {
        testCard.setDefinition("A tested definition");
        String newDefinition = testCard.getDefinition();
        assertEquals(newDefinition,"A tested definition");
    }
}