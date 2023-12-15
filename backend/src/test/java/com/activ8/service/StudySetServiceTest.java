package com.activ8.service;

import com.activ8.model.EDifficulty;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import com.activ8.model.StudySet;
import com.activ8.repository.FlashcardRepository;
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
    @Mock
    private FlashcardRepository flashcardRepository;

    @InjectMocks
    private StudySetService studySetService;
    private String userId="userId";
    private String studyFolderId="folderId";
    private String studySetId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveStudySet() {
        StudySet studySetToSave = new StudySet(studySetId,userId,studyFolderId,"test","A test set");
        when(studySetRepository.save(studySetToSave)).thenReturn(studySetToSave);

        StudySet savedStudySet = studySetService.saveStudySet(studySetToSave);

        assertNotNull(savedStudySet);
        assertEquals(studySetToSave, savedStudySet);
    }

    @Test
    void testGetAllStudySets() {
        List<StudySet> studySets = new ArrayList<>();
        StudySet testSet = new StudySet(studySetId,userId,studyFolderId,"test","A test set");
        StudySet testSet1 = new StudySet(studySetId,userId,studyFolderId,"test","A test set");
        studySets.add(testSet);
        studySets.add(testSet1);


        when(studySetRepository.findAllByStudyFolderId(studyFolderId)).thenReturn(studySets);

        List<StudySet> retrievedStudySets = studySetService.getAllStudySets(studyFolderId);

        assertEquals(studySets.size(), retrievedStudySets.size());
    }

    @Test
    void testGetStudySet() {
        String studySetId = "123";
        StudySet studySet = new StudySet(studySetId,userId,studyFolderId,"test","A test set");
        when(studySetRepository.findById(studySetId)).thenReturn(Optional.of(studySet));

        Optional<StudySet> retrievedStudySet = studySetService.getStudySet(studySetId);

        assertTrue(retrievedStudySet.isPresent());
        assertEquals(studySet, retrievedStudySet.get());
    }
    @Test
    void testDeleteStudySet() {
        StudySet studySet = new StudySet(userId,studyFolderId,"test","A test set");
        when(studySetRepository.findById(studySet.id())).thenReturn(Optional.of(studySet));

        List<Flashcard> flashcards = new ArrayList<>();
        Flashcard testFlashcard = new SimpleFlashcard(userId, studyFolderId, "test", "A card for testing", EDifficulty.MEDIUM);
        flashcardService.saveFlashcard(testFlashcard);
        flashcards.add(testFlashcard);

        when(flashcardService.getAllFlashcardsInStudySet(studySet.id())).thenReturn(flashcards);
        when(flashcardService.deleteFlashcard(userId, testFlashcard.getId())).thenReturn(true); // Mock the deleteFlashcard method

        boolean deleted = studySetService.deleteStudySet(userId, studySet.id());

        assertTrue(deleted);
    }
}
