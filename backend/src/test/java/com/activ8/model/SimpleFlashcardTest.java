package com.activ8.model;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleFlashcardTest {
    Flashcard testCard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
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
}
