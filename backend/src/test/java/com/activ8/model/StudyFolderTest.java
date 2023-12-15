/**
 * Test class to validate the functionality of StudyFolder class methods.
 */
package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyFolderTest {
    StudyFolder testFolder = new StudyFolder("studyFolderId", "userId", "test", "A test folder");

    /**
     * Tests the retrieval of the ID from the StudyFolder.
     */
    @Test
    void id() {
        String id = testFolder.id();
        assertEquals(id, "studyFolderId");
    }

    /**
     * Tests the retrieval of the User ID from the StudyFolder.
     */
    @Test
    void userId() {
        String userId = testFolder.userId();
        assertEquals(userId, "userId");
    }

    /**
     * Tests the retrieval of the title from the StudyFolder.
     */
    @Test
    void title() {
        String title = testFolder.title();
        assertEquals(title, "test");
    }

    /**
     * Tests the retrieval of the description from the StudyFolder.
     */
    @Test
    void description() {
        String description = testFolder.description();
        assertEquals(description, "A test folder");
    }
}