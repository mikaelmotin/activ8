package com.activ8.service;

import com.activ8.model.Flashcard;
import com.activ8.repository.FlashcardRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashcardService {

    @Autowired 
    FlashcardRepository flashcardRepository;

    public Flashcard saveFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }
    
    public Optional<Flashcard> getFlashcard(String flashcardId) {
        return flashcardRepository.findById(flashcardId);
    }

    public List<Flashcard> getAllFlashcardsByStudySetId(String studySetId) {
        return flashcardRepository.findAllByStudySetId(studySetId); 
    }

}
