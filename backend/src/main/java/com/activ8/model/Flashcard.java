package com.activ8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flashcards")
public abstract class Flashcard {

    @Id
    protected String id;
    protected String studySetId;
    protected String term;
    protected String definition;
    protected EDifficulty difficulty;

    public Flashcard() {

    }

    public Flashcard(String studySetId, String term, String definition,EDifficulty difficulty) {
        this.studySetId = studySetId;
        this.term = term;
        this.definition = definition;
        this.difficulty = difficulty;
    }
    public Flashcard(String id, String studySetId, String term, String definition, EDifficulty difficulty) {
        this.id = id;
        this.studySetId = studySetId;
        this.term = term;
        this.definition = definition;
        this.difficulty = difficulty;
    }
    public String getId() {
        return id;
    }
    public String getStudySetId() {
        return studySetId;
    }
    public String getTerm() {
        return term;
    }
    public String getDefinition() {
        return definition;
    }
    public EDifficulty getDifficulty() {
        return difficulty;
    }


}
