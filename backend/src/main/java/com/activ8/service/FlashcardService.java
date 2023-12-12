package com.activ8.service;

import com.activ8.model.Flashcard;
import com.activ8.repository.FlashcardRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FlashcardService {

    @Autowired 
    FlashcardRepository flashcardRepository;

    @Autowired
    StudySetService studySetService;

    private ObjectMapper objectMapper;

    @Transactional
    public Flashcard saveFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }
    
    @Transactional
    public Optional<Flashcard> getFlashcard(String flashcardId) {
        Optional<Flashcard> flashcard = flashcardRepository.findById(flashcardId);

        return flashcard;
    }

    @Transactional
    public List<Flashcard> getAllFlashcardsInStudySet(String studySetId) {
        List<Flashcard> flashcards = flashcardRepository.findAllByStudySetId(studySetId); 
        return flashcards; 
    }

    @Transactional
    public Boolean deleteFlashcard(String userID, String flashcardId) {
        Flashcard flashcardToDelete = flashcardRepository.findById(flashcardId).get();
        
        if(studySetService.getStudySet(flashcardToDelete.getStudySetId()).get().ownerId().equals(userID) 
            && flashcardRepository.existsById(flashcardId)) {
                flashcardRepository.delete(flashcardToDelete);
                return true;
        }
        
        
        return false;
    }

    public String convertFlashcardToJson(Flashcard flashcard) {
        try {
            return objectMapper.writeValueAsString(flashcard);
        } catch (Exception e) {
            e.printStackTrace();
            return "{error}";
        }
    }
}
