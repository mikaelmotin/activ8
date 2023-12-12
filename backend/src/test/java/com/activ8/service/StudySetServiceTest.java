package com.activ8.service;

import com.activ8.model.EDifficulty;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import com.activ8.model.StudySet;
import com.activ8.service.StudySetService;
import com.activ8.repository.StudySetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StudySetServiceTest {

    @Mock
    private StudySetRepository studySetRepository;
    @Mock
    private FlashcardService flashcardService;

    @InjectMocks
    private StudySetService studySetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveStudySet() {
        StudySet studySetToSave = new StudySet("404","100","101","test","A test set");
        when(studySetRepository.save(studySetToSave)).thenReturn(studySetToSave);

        StudySet savedStudySet = studySetService.saveStudySet(studySetToSave);

        assertNotNull(savedStudySet);
        assertEquals(studySetToSave, savedStudySet);
        verify(studySetRepository, times(1)).save(studySetToSave);
    }

    @Test
    void testGetAllStudySets() {
        String studyFolderId = "folder123";
        List<StudySet> studySets = new ArrayList<>();
        // Populate studySets list with test data

        when(studySetRepository.findAllByStudyFolderId(studyFolderId)).thenReturn(studySets);

        List<StudySet> retrievedStudySets = studySetService.getAllStudySets(studyFolderId);

        assertEquals(studySets.size(), retrievedStudySets.size());
        verify(studySetRepository, times(1)).findAllByStudyFolderId(studyFolderId);
    }

    @Test
    void testGetStudySet() {
        String studySetId = "123";
        StudySet studySet = new StudySet(studySetId,"100","101","test","A test set");
        when(studySetRepository.findById(studySetId)).thenReturn(Optional.of(studySet));

        Optional<StudySet> retrievedStudySet = studySetService.getStudySet(studySetId);

        assertTrue(retrievedStudySet.isPresent());
        assertEquals(studySet, retrievedStudySet.get());
        verify(studySetRepository, times(1)).findById(studySetId);
    }
    @Test
    void testDeleteStudySet() {
        String userId = "user123";
        String studySetId = "studySet456";

        StudySet studySet = new StudySet(studySetId,"100","101","test","A test set");
        when(studySetRepository.findById(studySetId)).thenReturn(Optional.of(studySet));

        List<Flashcard> flashcards = new ArrayList<>();
        Flashcard testFlashcard = new SimpleFlashcard("studySet456", "test", "A card for testing", EDifficulty.MEDIUM);
        flashcardService.saveFlashcard(testFlashcard);
        flashcards.add(testFlashcard);

        when(flashcardService.getAllFlashcardsInStudySet(studySetId)).thenReturn(flashcards);
        when(flashcardService.deleteFlashcard(userId, testFlashcard.getId())).thenReturn(true); // Mock the deleteFlashcard method

        boolean deleted = studySetService.deleteStudySet(userId, studySetId);

        assertTrue(deleted);
        verify(studySetRepository, times(1)).findById(studySetId);
        verify(flashcardService, times(flashcards.size())).deleteFlashcard(userId,testFlashcard.getId()); // Verify that deleteFlashcard was called for each flashcard
        verify(studySetRepository, times(1)).delete(studySet);
    }
}
