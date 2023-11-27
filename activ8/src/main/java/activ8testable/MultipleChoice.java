package activ8testable;
import java.util.*;

public class MultipleChoice {
    private String option1;
    private String option2;
    private String option3;
    private String definition;
    private String answer;
    private List<String> options = new ArrayList<>();
    private String difficulty;


        public MultipleChoice(String option1, String option2, String option3, String answer, String definition) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
        this.definition = definition;
        this.difficulty = "medium";
    }
        public String getAnswer() {
        return answer;
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getOptions(){
         Collections.addAll(options, option1, option2, option3, answer);
        return options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
