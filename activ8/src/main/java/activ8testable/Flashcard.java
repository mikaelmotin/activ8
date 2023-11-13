package activ8testable;

public class Flashcard {
    private String term;
    private String defintion;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.defintion = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefintion() {
        return defintion;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setDefintion(String defintion) {
        this.defintion = defintion;
    }
}