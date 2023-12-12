package com.activ8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activ8.model.StudySession;
import com.activ8.model.User;
import com.activ8.repository.StudySessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudySessionService {

    @Autowired
    private StudySessionRepository studySessionRepository;

    @Autowired
    public StudySessionService(StudySessionRepository studySessionRepository) {
        this.studySessionRepository = studySessionRepository;
    }

    public StudySession startStudySession(User user, StudySession studySession) {
        //studySession.setUser(user);
        //studySession.setStartTime(LocalDateTime.now());
        return studySessionRepository.save(studySession);
    }

    public void endStudySession(StudySession studySession) {
        //studySession.setEndTime(LocalDateTime.now());
        studySessionRepository.save(studySession);
    }

    public List<StudySession> getAllStudySessions() {
        return studySessionRepository.findAll();
    }

    public Optional<StudySession> getStudySessionById(String id) {
        return studySessionRepository.findById(id);
    }

    public void deleteStudySession(String id) {
        studySessionRepository.deleteById(id);
    }

    // Other methods as needed
}
