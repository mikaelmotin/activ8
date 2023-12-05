package com.activ8.model;


public class SimpleFlashcard extends Flashcard {
    
      public SimpleFlashcard() {
      super();
    }

    public SimpleFlashcard(String studySetId, String term, String definition, EDifficulty difficulty) {
        super(studySetId, term, definition,difficulty);

}
    public SimpleFlashcard(String flashcardId, String studySetId, String term, String definition, EDifficulty difficulty) {
          super(flashcardId, studySetId,term,definition,difficulty);
    }
}