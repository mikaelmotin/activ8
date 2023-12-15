package com.activ8.service;
import com.activ8.dto.FlashcardCheckDTO;
import com.activ8.model.EDifficulty;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import com.activ8.model.StudySet;
import com.activ8.repository.FlashcardRepository;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlashcardServiceTest {

    @Mock
    private FlashcardRepository flashcardRepository;

    @Mock
    private StudySetService studySetService;

    @InjectMocks
    private FlashcardService flashcardService;
    private String userId= "userId";
    private String studySetId="studySetId";
    private String flashcardId = "flashcardId";
    private String studyFolderId = "folderId";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveFlashcard() {
        Flashcard flashcardToSave = new SimpleFlashcard(studySetId, "test", "A card for testing", EDifficulty.MEDIUM);
        when(flashcardRepository.save(any(Flashcard.class))).thenReturn(flashcardToSave);

        Flashcard savedFlashcard = flashcardService.saveFlashcard(flashcardToSave);

        assertNotNull(savedFlashcard);
        assertEquals(flashcardToSave, savedFlashcard);
    }

    @Test
    void testGetFlashcard() {
        Flashcard flashcard = new SimpleFlashcard(studySetId, "test", "A card for testing",EDifficulty.MEDIUM);
        when(flashcardRepository.findById(flashcardId)).thenReturn(Optional.of(flashcard));

        Optional<Flashcard> retrievedFlashcard = flashcardService.getFlashcard(flashcardId);

        assertTrue(retrievedFlashcard.isPresent());
        assertEquals(flashcard, retrievedFlashcard.get());
    }

    @Test
    void testGetAllFlashcardsInStudySet() {
        List<Flashcard> flashcards = new ArrayList<>();
        Flashcard flashcard = new SimpleFlashcard(studySetId, "test", "A card for testing",EDifficulty.MEDIUM);
        flashcardService.saveFlashcard(flashcard);
        flashcards.add(flashcard);

        when(flashcardRepository.findAllByStudySetId(studySetId)).thenReturn(flashcards);

        List<Flashcard> retrievedFlashcards = flashcardService.getAllFlashcardsInStudySet(studySetId);

        assertEquals(flashcards.size(), retrievedFlashcards.size());
        verify(flashcardRepository, times(1)).findAllByStudySetId(studySetId);
    }

    @Test
    void testDeleteFlashcard() {

        Flashcard flashcard = new SimpleFlashcard("2222", "test", "A card for testing", EDifficulty.MEDIUM);
        StudySet testStudySet = new StudySet(studySetId, userId, studyFolderId, "test", "A test set");

        when(flashcardRepository.findById(flashcardId)).thenReturn(Optional.of(flashcard));
        when(studySetService.getStudySet(anyString())).thenReturn(Optional.of(testStudySet));
        when(flashcardRepository.existsById(flashcardId)).thenReturn(true);

        boolean deleted = flashcardService.deleteFlashcard(userId, flashcardId);

        assertTrue(deleted);
    }
}