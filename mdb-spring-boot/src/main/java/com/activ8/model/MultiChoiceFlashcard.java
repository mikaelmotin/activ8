package com.activ8.model;

import java.util.List;

public class MultiChoiceFlashcard extends Flashcard {
    
    private List<String> flashcardIds;

    public MultiChoiceFlashcard(String studySetId, String term, String definition, List<Flashcard> flashcardIds) {
        super(studySetId, term, definition);
    }
    
    public MultiChoiceFlashcard(String studySetId, String id, String term, String definition, List<Flashcard> flashcardIds) {
      super(id, studySetId, term, definition);
    }

    public List<String> getFlashcardIds() {
      return flashcardIds;
    }
}