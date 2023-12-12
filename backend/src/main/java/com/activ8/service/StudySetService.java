package com.activ8.service;

import java.util.List;
import java.util.Optional;

import com.activ8.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.model.StudySet;
import com.activ8.repository.StudySetRepository;
import com.activ8.service.FlashcardService;

@Service
public class StudySetService {

    @Autowired
    StudySetRepository studySetRepository;
    @Autowired
    FlashcardService flashcardService;

    @Transactional
    public StudySet saveStudySet(StudySet studySet) {
        return studySetRepository.save(studySet);
    }

    @Transactional
    public List<StudySet> getAllStudySets(String studyFolderId) {
        List<StudySet> studySets = studySetRepository.findAllByStudyFolderId(studyFolderId);

        return studySets;
    }

    @Transactional
    public Optional<StudySet> getStudySet(String studySetId) {
        Optional<StudySet> studySet = studySetRepository.findById(studySetId);

        return studySet;
    }
    @Transactional
    public boolean deleteStudySet(String userId,String studySetId) {
        Optional<StudySet> studySetOptional = studySetRepository.findById(studySetId);

        if (studySetOptional.isPresent()) {
            StudySet studySet = studySetOptional.get();
            List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet(studySetId);
            for (Flashcard flashcard : flashcards) {
                flashcardService.deleteFlashcard(userId,flashcard.getId());
            }
            studySetRepository.delete(studySet);
            return true;
        }
        return false;
    }
}

