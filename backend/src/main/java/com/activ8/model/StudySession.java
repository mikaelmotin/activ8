package com.activ8.model;
import com.activ8.repository.StudySetRepository;
import com.activ8.service.FlashcardService;
import com.activ8.service.UserDetailsImpl;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudySession {

    @Id
    private String sessionId;
    private StudySetRepository studySetRepository;
    private FlashcardService flashcardService; // Inject FlashcardService
    private Optional<StudySet> studySet;
    private FlashcardFrequencyManager flashcardFrequencyManager;
    private String userId;
    private List<Flashcard> flashcardsToStudy;
    private int currentIndex;

    public StudySession(StudySetRepository studySetRepository, FlashcardService flashcardService, String studySetId, FlashcardFrequencyManager flashcardFrequencyManager, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.studySetRepository = studySetRepository;
        this.flashcardService = flashcardService; // Initialize FlashcardService
        this.flashcardFrequencyManager = flashcardFrequencyManager;
        this.userId = userDetails.getId();

        this.startNewSession(studySetId, userDetails);
    }

    private void startNewSession(String studySetId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<StudySet> studySetOptional = studySetRepository.findById(studySetId);
        if (studySetOptional.isPresent()) {
            this.studySet = studySetOptional;
            this.userId = userDetails.getId();
            this.flashcardsToStudy = new ArrayList<>();
            this.currentIndex = 0;

            // Fetch flashcards associated with the study set using FlashcardService
            List<Flashcard> fetchedFlashcards = flashcardService.getAllFlashcardsInStudySet(studySetId);
            this.flashcardsToStudy.addAll(fetchedFlashcards);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public Flashcard getNextFlashcard() {
        if (currentIndex < flashcardsToStudy.size()) {
            return flashcardsToStudy.get(currentIndex++);
        }
        return null;
    }

    // Add methods to manage the session, progress through flashcards, etc.
}