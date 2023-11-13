package activ8testable;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {
    StudySet test = new StudySet("test");
    @Test
    void addFlashcard() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        test.addFlashcard(testCard);
        int size = test.getSize();
        assertEquals(size,1);
    }

    @Test
    void removeFlashcard() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        test.addFlashcard(testCard);
        test.removeFlashcard(testCard);
        int size = test.getSize();
        assertEquals(size,0);
    }

    @Test
    void editFlashcard() {
    } //remove

    @Test
    void editTitle() {
        test.editTitle("tested");
        String title = test.getTitle();
        assertEquals(title, "tested");
    }

    @Test
    void setDescription() {
        test.setDescription("test description");
        String description = test.getDescription();
        assertEquals(description,"test description");
    }

    @Test
    void getFlashcardsList() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        test.addFlashcard(testCard);
        List<Flashcard> testList = new ArrayList<>();
        testList.add(testCard);
        assertEquals(test.getFlashcardsList(),testList);

    }

    @Test
    void getTitle() {
        String title = test.getTitle();
        assertEquals(title, "test");
    }
}