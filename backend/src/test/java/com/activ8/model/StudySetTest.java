package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {
    StudySet testSet = new StudySet("404","100","101","test","A test set");
    @Test
    void id() {
        String Id = testSet.id();
        assertEquals(Id, "404");
    }

    @Test
    void ownerId() {
        String ownerId = testSet.ownerId();
        assertEquals(ownerId, "100");
    }

    @Test
    void studyFolderId() {
        String studyFolderId = testSet.studyFolderId();
        assertEquals(studyFolderId, "101");
    }

    @Test
    void title() {
        String title = testSet.title();
        assertEquals(title, "test");
    }

    @Test
    void description() {
        String description = testSet.description();
        assertEquals(description,"A test set");
    }
}