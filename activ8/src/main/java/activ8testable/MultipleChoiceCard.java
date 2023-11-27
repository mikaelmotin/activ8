package activ8testable;
import java.util.*;

public class MultipleChoiceCard implements MultipleChoiceINTERFACE{
    private String option1;
    private String option2;
    private String option3;
    private String definition;
    private String term;
    private List<String> options = new ArrayList<>();
    private String difficulty;

    public MultipleChoiceCard(String definition, String term,  String option1, String option2, String option3) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.term = term;
        this.definition = definition;
        this.difficulty = "medium";
    }
    @Override
    public String getTerm() {
        return term;
    }

    @Override
    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    @Override
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public String getDifficulty() {
        return this.difficulty;
    }

    @Override
    public void setDifficulty(String difficulty) {
        if (difficulty.equals("easy") || difficulty.equals("medium")|| difficulty.equals("hard")){
            this.difficulty = difficulty;
        }
        else throw new IllegalArgumentException();
    }
    @Override
    public List<String> getOptions(){
        Collections.addAll(options, option1, option2, option3, term);
        return options;
    }
}