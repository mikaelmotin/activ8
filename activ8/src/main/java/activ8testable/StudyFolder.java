package activ8testable;

import java.util.ArrayList;

/*
 * StudyFolder - Has a title, can have a description and stores study sets
 */
public class StudyFolder {
    private String title;
    private String description;
    private ArrayList<StudySet> studySets = new ArrayList<>();
    

    public StudyFolder(String title) {
        this.title = title;
    }
    public void addStudySet(StudySet studySet){
        studySets.add(studySet);
    }
    public void removeStudySet(StudySet studySet){
        studySets.remove(studySet);
    }
    public void editStudySet(StudySet studySet, String term, String description){
        studySet.editTitle(term);
        studySet.setDescription(description);
    } //bad design?
    public void editTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription(){return this.description;}
    public ArrayList<StudySet> getStudySets() {
        return studySets;
    }
    public int size(){return studySets.size();}
    public String getTitle() {
        return this.title;
    }
}
