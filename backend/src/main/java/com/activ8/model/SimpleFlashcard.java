package com.activ8.model;


public class SimpleFlashcard extends Flashcard {
    
      public SimpleFlashcard() {
      super();
    }

    public SimpleFlashcard(String studySetId, String term, String definition) {
        super(studySetId, term, definition);
    public SimpleFlashcard(String studySetId, String term, String definition, EDifficulty difficulty) {
        super(studySetId, term, definition, difficulty);
    }
    
    public SimpleFlashcard(String id,String studySetId, String term, String definition, EDifficulty difficulty) {
      super(id, studySetId, term, definition, difficulty);
    }
}