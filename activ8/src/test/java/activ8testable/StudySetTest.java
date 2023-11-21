package activ8testable;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {
    StudySetFlashcard test = new StudySetFlashcard("test");

    //Tests that the created StudySet is created with the correct title.
    @Test
    void StudySetConstructor() {
        assertEquals(test.getTitle(),"test");
    }
    //Tests that the getSize() method returns the correct size.
    @Test
    void getSize() {
        int size = test.getSize();
        assertEquals(size, 0);
    }
    //Tests that the addCard() adds a Flashcard to the StudySet.
    @Test
    void addCardForFlashcard() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        test.addCard(testCard);
        int size = test.getSize();
        assertEquals(size,1);
    }
    //Tests that the addFlashcard() removes a Flashcard to the StudySet.
    @Test
    void removeCardForFlashcard() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        test.addCard(testCard);
        test.removeCard(testCard);
        int size = test.getSize();
        assertEquals(size,0);
    }
    //Tests that the editTitle() changes the title of the StudySet to a given title.
    @Test
    void editTitle() {
        test.editTitle("tested");
        String title = test.getTitle();
        assertEquals(title, "tested");
    }
    //Tests that the setDescription() changes the description of the StudySet to a given description.
    @Test
    void setDescription() {
        test.setDescription("test description");
        String description = test.getDescription();
        assertEquals(description,"test description");
    }
    //Tests that the getFlashcardsList() method returns a list of Flashcards that are in the StudySet.
    @Test
    void getCardsListForFlashcard() {
        Flashcard testCard = new Flashcard("testCard","A card for testing");
        testCard.setDifficulty("medium");
        test.addCard(testCard);
        List<Flashcard> testList = new ArrayList<>();
        testList.add(testCard);
        assertEquals(test.getCardsList("medium"),testList);

    }
    //Tests that the getTitle() returns the title of the StudySet.
    @Test
    void getTitle() {
        String title = test.getTitle();
        assertEquals(title, "test");
    }
}