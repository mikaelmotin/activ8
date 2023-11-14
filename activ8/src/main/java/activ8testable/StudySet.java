package activ8testable;

import java.util.ArrayList;
import java.util.List;

/*
 * StudySet - Has a title, can have a description and stores flashcards
 */
public class StudySet {
    private String title;
    private String description;
    private List<Flashcard> flashcardsList = new ArrayList<>();

    public StudySet(String title) {
        this.title = title;
    }
    public void addFlashcard(Flashcard card){
        flashcardsList.add(card);
    }
    public void removeFlashcard(Flashcard card){
        flashcardsList.remove(card);
    }
    public void editFlashcard(Flashcard card, String term, String definition){
        card.setTerm(term);
        card.setDefinition(definition);
    } //not good object-oriented!
    public void editTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() { return this.description;}
    public List<Flashcard> getFlashcardsList() {
        return flashcardsList;
    }
    public String getTitle() {
        return this.title;
    }
    public int getSize() {
        return flashcardsList.size();
    }
}