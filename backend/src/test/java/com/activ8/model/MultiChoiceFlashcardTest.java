package com.activ8.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiChoiceFlashcardTest {

    List<Flashcard> flashcardList = new ArrayList<>();
    private String studySetId = "studySetId";
    @Test
    void TestCreateMultiChoiceFlashcard(){
        Flashcard fakeOption1 = new SimpleFlashcard(studySetId, "fake1", "A card for testing",EDifficulty.MEDIUM);
        Flashcard fakeOption2 = new SimpleFlashcard(studySetId, "fake2", "A card for testing",EDifficulty.MEDIUM);
        Flashcard fakeOption3 = new SimpleFlashcard(studySetId, "fake3", "A card for testing",EDifficulty.MEDIUM);
        flashcardList.add(fakeOption1);
        flashcardList.add(fakeOption2);
        flashcardList.add(fakeOption3);
        Flashcard multiChoiceFlashcard = new MultiChoiceFlashcard(studySetId, "test", "A card for testing",EDifficulty.MEDIUM,flashcardList);
        assertNotNull(multiChoiceFlashcard);
    }
}
