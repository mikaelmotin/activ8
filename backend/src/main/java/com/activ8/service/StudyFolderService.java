package com.activ8.service;

import java.util.List;
import java.util.Optional;

import com.activ8.model.StudySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.model.StudyFolder;
import com.activ8.repository.StudyFolderRepository;
import com.activ8.service.StudySetService;

@Service
public class StudyFolderService {

    @Autowired
    StudyFolderRepository studyFolderRepository;
    @Autowired
    StudySetService studySetService;

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
    public boolean deleteStudyFolder(String userId, String folderId) {
        Optional<StudyFolder> folderOptional = studyFolderRepository.findById(folderId);

        if (folderOptional.isPresent()) {
            StudyFolder folder = folderOptional.get();

            List<StudySet> studySets = studySetService.getAllStudySets(folderId); // Assuming StudyFolder has a method to get study sets

            for (StudySet studySet : studySets) {
                studySetService.deleteStudySet(userId, studySet.id());
            }

            studyFolderRepository.delete(folder);

            return true;
        }

        return false;
    }

}
