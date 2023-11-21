package activ8testable;

import java.util.ArrayList;

/*
 * StudyFolder - Has a title, can have a description and stores study sets
 */
public class StudyFolder {
    private String title;
    private String description;
    private ArrayList<StudySetFlashcard> studySetFlashcards = new ArrayList<>();
    

    public StudyFolder(String title) {
        this.title = title;
    }
    public void addStudySet(StudySetFlashcard studySetFlashcard){
        studySetFlashcards.add(studySetFlashcard);
    }
    public void removeStudySet(StudySetFlashcard studySetFlashcard){
        studySetFlashcards.remove(studySetFlashcard);
    }
    public void editStudySet(StudySetFlashcard studySetFlashcard, String term, String description){
        studySetFlashcard.editTitle(term);
        studySetFlashcard.setDescription(description);
    } //bad design?
    public void editTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription(){return this.description;}
    public ArrayList<StudySetFlashcard> getStudySets() {
        return studySetFlashcards;
    }
    public int size(){return studySetFlashcards.size();}
    public String getTitle() {
        return this.title;
    }
}
