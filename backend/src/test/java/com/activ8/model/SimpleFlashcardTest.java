/**
 * Test class to validate the functionality of SimpleFlashcard class methods.
 */
package com.activ8.model;

import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleFlashcardTest {
    Flashcard testCard = new SimpleFlashcard("studySetId", "test", "A card for testing", EDifficulty.MEDIUM);

    /**
     * Tests the retrieval of the term from the SimpleFlashcard.
     */
    @Test
    void getTerm() {
        String term = testCard.getTerm();
        assertEquals(term, "test");
    }

    /**
     * Tests the retrieval of the definition from the SimpleFlashcard.
     */
    @Test
    void getDefinition() {
        String definition = testCard.getDefinition();
        assertEquals(definition, "A card for testing");
    }

    /**
     * Tests the retrieval of the difficulty level from the SimpleFlashcard.
     */
    @Test
    void getDifficulty(){
        EDifficulty difficulty = testCard.getDifficulty();
        assertEquals(difficulty, EDifficulty.MEDIUM);
    }
}