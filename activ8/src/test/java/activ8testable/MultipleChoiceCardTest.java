package activ8testable;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceCardTest {
    MultipleChoiceCard MCC = new MultipleChoiceCard("A testable MCC", "testMCC", "horse", "dog", "cat");
    @Test
    void MultipleChoiceCardConstructor(){
    assertEquals(MCC.getDefinition(),"A testable MCC");
    assertEquals(MCC.getTerm(),"testMCC");
    List<String> allOptions = MCC.getOptions();
    assertEquals(allOptions.get(0),"horse");
    assertEquals(allOptions.get(1),"dog");
    assertEquals(allOptions.get(2),"cat");
    }
    @Test
    void getTerm() {
        assertEquals(MCC.getTerm(),"testMCC");
    }

    @Test
    void setTerm() {
        String newTerm = "tested";
        MCC.setTerm(newTerm);
        assertEquals(MCC.getTerm(),"tested");
    }

    @Test
    void getDefinition() {
        assertEquals(MCC.getDefinition(),"A testable MCC");
    }

    @Test
    void setDefinition() {
        String newDefinition = "A tested MCC";
        MCC.setDefinition(newDefinition);
        assertEquals(MCC.getDefinition(),"A tested MCC");
    }

    @Test
    void getDifficulty() {
        assertEquals(MCC.getDifficulty(),"medium");
    }
    @Test
    void setDifficulty() {
        String newDifficulty = "hard";
        MCC.setDifficulty(newDifficulty);
        assertEquals(MCC.getDifficulty(),"hard");
    }
    @Test
    void setDifficultyExceptionThrow(){
        assertThrows(IllegalArgumentException.class,()->MCC.setDifficulty("impossible"));

    }
    @Test
    void getOptions(){
        List<String> allOptions = MCC.getOptions();
        assertEquals(allOptions.get(0),"horse");
        assertEquals(allOptions.get(1),"dog");
        assertEquals(allOptions.get(2),"cat");
        assertEquals(allOptions.get(3),"testMCC");
    }
}