package com.activ8.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.model.Flashcard;
import com.activ8.model.StudyFolder;
import com.activ8.repository.StudyFolderRepository;

@Service
public class StudyFolderService {

    @Autowired
    StudyFolderRepository studyFolderRepository;

    @Transactional
    public StudyFolder saveStudyFolder(StudyFolder studyFolder) {
        return studyFolderRepository.save(studyFolder);
    }

    @Transactional
    public List<StudyFolder> getAllStudyFolders(String userId) {
        List<StudyFolder> studyFolders = studyFolderRepository.findAllByUserId(userId);

        return studyFolders;
    }

    @Transactional
    public Optional<StudyFolder> getStudyFolder(String studyFolderId) {
        Optional<StudyFolder> studyFolder = studyFolderRepository.findById(studyFolderId);

        return studyFolder;
    }

    @Transactional
    public Boolean deleteStudyFolder(String userID, String studyFolderId) {
        StudyFolder studyFolderToDelete = studyFolderRepository.findById(studyFolderId).get();
        
        if(studyFolderToDelete.userId().equals(userID) 
            && studyFolderRepository.existsById(studyFolderId)) {
                studyFolderRepository.delete(studyFolderToDelete);
                return true;
        }
        
        
        return false;
    }

}
