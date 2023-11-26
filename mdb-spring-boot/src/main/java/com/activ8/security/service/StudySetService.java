package com.activ8.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.model.StudySet;
import com.activ8.repository.StudySetRepository;

@Service
public class StudySetService {

    @Autowired
    StudySetRepository studySetRepository;

    @Transactional
    public StudySet saveStudySet(StudySet studySet) {
        return studySetRepository.save(studySet);
    }

    @Transactional
    public List<StudySet> getAllStudySets(String studyFolderId) {
        List<StudySet> studySets = studySetRepository.findAllByStudyFolderId(studyFolderId);

        return studySets;
    }

    @Transactional
    public Optional<StudySet> getStudySet(String studySetId) {
        Optional<StudySet> studySet = studySetRepository.findById(studySetId);

        return studySet;
    }
}
