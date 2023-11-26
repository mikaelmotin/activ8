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

    public Flashcard(String studySetId, String term, String definition) {
        this.studySetId = studySetId;
        this.term = term;
        this.definition = definition;
    }
    public Flashcard(String id, String studySetId, String term, String definition) {
        this.id = id;
        this.studySetId = studySetId;
        this.term = term;
        this.definition = definition;
    }
    public String getId() {
        return id;
    }
    public String getTerm() {
        return term;
    }
    public String getDefinition() {
        return definition;
    }

}
