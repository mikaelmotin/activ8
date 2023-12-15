package com.activ8.view;
import com.activ8.model.StudyFolder;
import com.activ8.model.StudySet;
import com.activ8.service.StudySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudyFolderView {
    @Autowired
    StudySetService studySetService;

    public void displayStudyFolderDetails(StudyFolder studyFolder) {
        System.out.println("Study Folder Title: " + studyFolder.title());
        System.out.println("Study Folder Description: " + studyFolder.description());
        System.out.println("-------------");
    }

    public void displayStudyFolderTitle(StudyFolder studyFolder) {
        System.out.println("Study Folder Title: " + studyFolder.title());
    }

    public void displayStudyFolderDescription(StudyFolder studyFolder) {
        System.out.println("Study Folder Description: " + studyFolder.description());
    }
    public void displayAllStudySets(StudyFolder studyFolder) {
        List<StudySet> studySets = studySetService.getAllStudySets(studyFolder.id());

        if (studySets.isEmpty()) {
            System.out.println("No study sets found in " + studyFolder.title());
        } else {
            System.out.println("Study Sets in " + studyFolder.title() + ":");
            for (int i = 0; i < studySets.size(); i++) {
                StudySet set = studySets.get(i);
                System.out.println((i + 1) + ". Title: " + set.title() + ", Description: " + set.description());
            }
        }
    }
    public void displayStudyFolderOptions(){
        System.out.println("\nStudy Folder options:");
        System.out.println("1. Choose a folder");
        System.out.println("2. Add a new folder");
        System.out.println("3. Remove a folder");
        System.out.println("4. Sign Out");

        System.out.print("Enter your choice: ");
    }
    public void displayChooseFolderMessage(){System.out.println("\nChoose a folder:");}
    public void displaySelectedFolder(StudyFolder selectedFolder){System.out.println("Selected Folder: " + selectedFolder.title());}
}