package com.activ8.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyFolderTest {
    StudyFolder testFolder = new StudyFolder("111", "100", "test", "A test folder");

    @Test
    void id() {
        String id = testFolder.id();
        assertEquals(id,"111");
    }

    @Test
    void userId() {
        String userId = testFolder.userId();
        assertEquals(userId,"100");
    }

    @Test
    void title() {
        String title = testFolder.title();
        assertEquals(title,"test");
    }

    @Test
    void description() {
        String description = testFolder.description();
        assertEquals(description, "A test folder");
    }
}