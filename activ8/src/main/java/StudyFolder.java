import java.util.ArrayList;
import java.util.List;

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
    public void removestudySet(StudySet studySet){
        studySets.remove(studySet);
    }
    public void editStudySet(StudySet studySet, String term, String description){
        studySet.editTitle(term);
        studySet.setDescription(description);
    }
    public void editTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<StudySet> getStudysets() {
        return studySets;
    }
    public String getTitle() {
        return this.title;
    }
}
