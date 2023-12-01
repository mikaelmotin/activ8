package com.activ8.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.activ8.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.activ8.repository.StudySetRepository;
import com.activ8.service.FlashcardService;

public class OptionGeneratorTest {

    @Mock
    private StudySetRepository studySetRepository;

    @Mock
    private FlashcardService flashcardService;

    @InjectMocks
    private OptionGenerator optionGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        optionGenerator = new OptionGenerator(studySetRepository, flashcardService);
    }

    @Test
    public void testGenerateOptions() {
        // Mock data for testing
        String studySetId = "studySetId";
        String currentFlashcardTerm = "currentTerm";

        StudySet studySet = new StudySet(studySetId,"100","101","test","A test set");

        Flashcard flashcard1 = new SimpleFlashcard("studySetId", "term1", "definition1", EDifficulty.MEDIUM);
        Flashcard flashcard2 = new SimpleFlashcard("studySetId", "term2", "definition2", EDifficulty.MEDIUM);
        Flashcard flashcard3 = new SimpleFlashcard("studySetId", "term3", "definition3", EDifficulty.MEDIUM);

        List<Flashcard> mockFlashcards = Arrays.asList(flashcard1, flashcard2, flashcard3);

        when(studySetRepository.findById(studySetId)).thenReturn(Optional.of(studySet));
        when(flashcardService.getAllFlashcardsByStudySetId(studySetId)).thenReturn(mockFlashcards);

        List<String> options = optionGenerator.generateOptions(studySetId, currentFlashcardTerm);

        assertNotNull(options);
        assertEquals(3, options.size());
        assertFalse(options.contains(currentFlashcardTerm)); // Ensure currentTerm is not in options
    }
}