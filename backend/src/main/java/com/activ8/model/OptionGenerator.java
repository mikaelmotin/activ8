package com.activ8.model;

import com.activ8.service.FlashcardService;
import com.activ8.repository.StudySetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class OptionGenerator {
    @Autowired
    private StudySetRepository studySetRepository;
    @Autowired
    private FlashcardService flashcardService;

    public OptionGenerator(StudySetRepository studySetRepository, FlashcardService flashcardService) {
        this.studySetRepository = studySetRepository;
        this.flashcardService = flashcardService;
    }

    public List<String> generateOptions(String studySetId, String currentFlashcardTerm) {
        Optional<StudySet> optionalStudySet = studySetRepository.findById(studySetId);

        if (optionalStudySet.isPresent()) {
            StudySet studySet = optionalStudySet.get();
            List<String> options = new ArrayList<>();
            List<Flashcard> flashcards = flashcardService.getAllFlashcardsByStudySetId(studySetId);

            if (flashcards.size() >= 3) {
                Random random = new Random();

                int index1, index2, index3;
                do {
                    index1 = random.nextInt(flashcards.size());
                    index2 = random.nextInt(flashcards.size());
                    index3 = random.nextInt(flashcards.size());
                } while (index1 == index2 || index1 == index3 || index2 == index3
                        || flashcards.get(index1).getTerm().equals(currentFlashcardTerm)
                        || flashcards.get(index2).getTerm().equals(currentFlashcardTerm)
                        || flashcards.get(index3).getTerm().equals(currentFlashcardTerm));

                options.add(flashcards.get(index1).getTerm());
                options.add(flashcards.get(index2).getTerm());
                options.add(flashcards.get(index3).getTerm());

                return options;
            } else {
                System.out.println("Study Set doesn't have enough flashcards.");
            }
        } else {
            System.out.println("Study Set not found.");
        }

        return null;
    }
}