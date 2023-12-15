/**
 * Test class to verify the functionality of StudySet class methods.
 */
package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {
    StudySet testSet = new StudySet("studySetId","userId","studyFolderId","test","A test set");

    /**
     * Tests the retrieval of the ID from the StudySet.
     */
    @Test
    void id() {
        String Id = testSet.id();
        assertEquals(Id, "studySetId");
    }

    /**
     * Tests the retrieval of the owner ID from the StudySet.
     */
    @Test
    void ownerId() {
        String ownerId = testSet.ownerId();
        assertEquals(ownerId, "userId");
    }

    /**
     * Tests the retrieval of the study folder ID from the StudySet.
     */
    @Test
    void studyFolderId() {
        String studyFolderId = testSet.studyFolderId();
        assertEquals(studyFolderId, "studyFolderId");
    }

    /**
     * Tests the retrieval of the title from the StudySet.
     */
    @Test
    void title() {
        String title = testSet.title();
        assertEquals(title, "test");
    }

    /**
     * Tests the retrieval of the description from the StudySet.
     */
    @Test
    void description() {
        String description = testSet.description();
        assertEquals(description,"A test set");
    }
}