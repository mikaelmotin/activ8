package com.activ8.model;

import com.activ8.repository.StudySetRepository;
import com.activ8.service.UserDetailsImpl;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Optional;

public class StudySession {
    @Id
    private String SessionId;
    private Optional<StudySet> studySet;
    private StudySetRepository studySetRepository;
    private String userId;


    public StudySession() {
    }
//User?
    public StudySession(StudySetRepository studySetRepository,String studySetId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.studySetRepository = studySetRepository;
        this.studySet = studySetRepository.findById(studySetId);
        this.userId = userDetails.getId();
    }


    public Optional<StudySet> getFlashcards() {
        return studySet;
    }


}
