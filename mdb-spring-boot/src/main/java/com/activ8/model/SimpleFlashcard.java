package com.activ8.model;


public class SimpleFlashcard extends Flashcard {
    
    public SimpleFlashcard(String studySetId, String term, String definition) {
        super(studySetId, term, definition);
    }
    
    public SimpleFlashcard(String id,String studySetId, String term, String definition) {
      super(id, studySetId, term, definition);
    }
}