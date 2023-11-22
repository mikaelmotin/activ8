package activ8testable;

public class Flashcard implements FlashcardINTERFACE{
    private String term;
    private String definition;
    private String difficulty;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.difficulty = "medium";
    }
    public Flashcard(String term, String definition,String difficulty) {
        this.term = term;
        this.definition = definition;
        this.difficulty = difficulty;
    }
    @Override
    public String getTerm() {
        return term;
    }
    @Override

    public String getDefinition() {
        return definition;
    }
    @Override

    public void setTerm(String term) {
        this.term = term;
    }
    @Override
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String getDifficulty() { return this.difficulty; }

    @Override
    public void setDifficulty(String difficulty) {
        if (difficulty.equals("easy") || difficulty.equals("medium")|| difficulty.equals("hard")){
            this.difficulty = difficulty;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}