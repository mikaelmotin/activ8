package com.activ8.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiChoiceFlashcardTest {

    @Test
    void getFlashcardIds() {
        List<Flashcard> flashcardList = new ArrayList<>();
        Flashcard testCard = new SimpleFlashcard("1111", "test", "A card for testing",EDifficulty.MEDIUM);
        flashcardList.add(testCard);
        MultiChoiceFlashcard MCF = new MultiChoiceFlashcard("222","test","A test MCF", EDifficulty.MEDIUM,flashcardList);
        assertNull(MCF.getFlashcardIds());
    }
}