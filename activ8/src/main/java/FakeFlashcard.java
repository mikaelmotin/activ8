public class FakeFlashcard {
    private String question;
    private String answer;

    // Default constructor

    // Constructor with parameters
    public FakeFlashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}