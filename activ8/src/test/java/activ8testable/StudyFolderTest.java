package activ8testable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyFolderTest {
    StudyFolder testFolder = new StudyFolder("test");

    @Test
    void size() {
        assertEquals(testFolder.size(),0);
        StudySet testSet = new StudySet("testSet");
        testFolder.addStudySet(testSet);
        assertEquals(testFolder.size(),1);
    }

    @Test
    void addStudySet() {
        StudySet testSet = new StudySet("testSet");
        testFolder.addStudySet(testSet);
        assertEquals(testFolder.size(),1);
    }

    @Test
    void removeStudySet() {
        StudySet testSet = new StudySet("testSet");
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
        assertEquals(description,"");
    }

    @Test
    void setDescription() {
        String description = "testDescription";
        testFolder.setDescription(description);
        assertEquals(testFolder.getDescription(),"testDescription");
    }

    @Test
    void getStudysets() {
    }

    @Test
    void getTitle() {
    }
}