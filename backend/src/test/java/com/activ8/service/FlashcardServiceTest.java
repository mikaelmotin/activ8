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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveFlashcard() {
        Flashcard flashcardToSave = new SimpleFlashcard("2222", "test", "A card for testing", EDifficulty.MEDIUM);
        when(flashcardRepository.save(any(Flashcard.class))).thenReturn(flashcardToSave);

        Flashcard savedFlashcard = flashcardService.saveFlashcard(flashcardToSave);

        assertNotNull(savedFlashcard);
        assertEquals(flashcardToSave, savedFlashcard);
        verify(flashcardRepository, times(1)).save(any(Flashcard.class));
    }

    @Test
    void testGetFlashcard() {
        String flashcardId = "123";
        Flashcard flashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        when(flashcardRepository.findById(flashcardId)).thenReturn(Optional.of(flashcard));

        Optional<Flashcard> retrievedFlashcard = flashcardService.getFlashcard(flashcardId);

        assertTrue(retrievedFlashcard.isPresent());
        assertEquals(flashcard, retrievedFlashcard.get());
        verify(flashcardRepository, times(1)).findById(flashcardId);
    }

    @Test
    void testGetAllFlashcardsInStudySet() {
        String studySetId = "456";
        List<Flashcard> flashcards = new ArrayList<>();
        // Add some flashcards to the list

        when(flashcardRepository.findAllByStudySetId(studySetId)).thenReturn(flashcards);

        List<Flashcard> retrievedFlashcards = flashcardService.getAllFlashcardsInStudySet(studySetId);

        assertEquals(flashcards.size(), retrievedFlashcards.size());
        verify(flashcardRepository, times(1)).findAllByStudySetId(studySetId);
    }

    @Test
    void testGuessTerm() {
        FlashcardCheckDTO flashcardCheckDTO = new FlashcardCheckDTO("test", "123");
        String flashcardId = flashcardCheckDTO.flashcardId();
        String guess = flashcardCheckDTO.guess();

        Flashcard flashcard = new SimpleFlashcard("2222", "test", "A card for testing",EDifficulty.MEDIUM);
        when(flashcardRepository.findById(flashcardId)).thenReturn(Optional.of(flashcard));

        boolean result = flashcardService.guessTerm(flashcardCheckDTO);

        assertTrue(result); // Assuming the guess matches the flashcard term
        verify(flashcardRepository, times(1)).findById(flashcardId);
    }

    @Test
    void testDeleteFlashcard() {
        String userId = "user123";
        String flashcardId = "flashcard456";

        Flashcard flashcard = new SimpleFlashcard("2222", "test", "A card for testing", EDifficulty.MEDIUM);
        StudySet mockStudySet = new StudySet("2222", userId, "101", "test", "A test set");

        when(flashcardRepository.findById(flashcardId)).thenReturn(Optional.of(flashcard));
        when(studySetService.getStudySet(anyString())).thenReturn(Optional.of(mockStudySet));
        when(flashcardRepository.existsById(flashcardId)).thenReturn(true);

        boolean deleted = flashcardService.deleteFlashcard(userId, flashcardId);

        assertTrue(deleted);
        verify(flashcardRepository, times(1)).findById(flashcardId);
        verify(studySetService, times(1)).getStudySet(anyString()); // Verify interaction with getStudySet method
        verify(flashcardRepository, times(1)).delete(flashcard);
    }
}