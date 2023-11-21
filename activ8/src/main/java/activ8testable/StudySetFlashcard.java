package activ8testable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * StudySet - Has a title, can have a description and stores flashcards
 */
public class StudySetFlashcard implements StudySet{
    private String title;
    private String description;
    private HashMap<String,Card> flashcardsList = new HashMap<>();

    public StudySetFlashcard(String title) {
        this.title = title;
    }

    @Override
    public void removeCard(Card card) { flashcardsList.remove(card.getDifficulty(),card);}

    @Override
    public void addCard(Card card) { flashcardsList.put(card.getDifficulty(),card); }

    @Override
    public void editTitle(String title) {
        this.title = title;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String getDescription() { return this.description;}

    @Override
    public List<Card> getCardsList(String difficulty) {
        List<Card>allFlashcards = new ArrayList<>();
        while (flashcardsList.get(difficulty) instanceof Card){
            allFlashcards.add(flashcardsList.get(difficulty));
            flashcardsList.remove(difficulty);
        }
        return allFlashcards;
    }
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public int getSize() {
        return flashcardsList.size();
    }
}