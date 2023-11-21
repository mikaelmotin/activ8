package activ8testable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyFolderTest {
    StudyFolder testFolder = new StudyFolder("test");

    @Test
    void size() {
        assertEquals(testFolder.size(),0);
        StudySetFlashcard testSet = new StudySetFlashcard("testSet");
        testFolder.addStudySet(testSet);
        assertEquals(testFolder.size(),1);
    }

    @Test
    void addStudySet() {
        StudySetFlashcard testSet = new StudySetFlashcard("testSet");
        testFolder.addStudySet(testSet);
        assertEquals(testFolder.size(),1);
    }

    @Test
    void removeStudySet() {
        StudySetFlashcard testSet = new StudySetFlashcard("testSet");
        testFolder.addStudySet(testSet);
        assertEquals(testFolder.size(),1);
        testFolder.removeStudySet(testSet);
        assertEquals(testFolder.size(),0);
    }

    @Test
    void editTitle() {
        String newTitle = "tested";
        testFolder.editTitle(newTitle);
        assertEquals(testFolder.getTitle(),"tested");
    }

    @Test
    void getDescription() {
        String description = testFolder.getDescription();
        assertNull(description);
        testFolder.setDescription("testDescription");
        assertEquals(testFolder.getDescription(),"testDescription");
    }

    @Test
    void setDescription() {
        String description = "testDescription";
        testFolder.setDescription(description);
        assertEquals(testFolder.getDescription(),"testDescription");
    }

    @Test
    void getStudySets() {
        StudySetFlashcard testSet = new StudySetFlashcard("testSet");
        testFolder.addStudySet(testSet);
        List<StudySetFlashcard> testList = new ArrayList<>();
        testList.add(testSet);
        assertEquals(testFolder.getStudySets(),testList);
    }

    @Test
    void getTitle() {
        String title = testFolder.getTitle();
        assertEquals(title,"test");
    }
}