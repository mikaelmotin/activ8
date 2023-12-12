package com.activ8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activ8.model.FreeRoamStudySession;
import com.activ8.model.StudySession;
import com.activ8.model.StudySessionLog;

import com.activ8.repository.StudySessionLogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudySessionService {
    
    private StudySession studySession;

    @Autowired
    private StudySessionLogRepository studySessionRepository;

    @Autowired
    public StudySessionService(StudySessionLogRepository studySessionRepository) {
        this.studySessionRepository = studySessionRepository;
    }

    public void startFreeRoamStudySession(String userId) {
        this.studySession = new FreeRoamStudySession(userId);
        studySession.start(userId);
    }

    public void nextCard() {
        studySession.nextCard();
    }

    public void endStudySession(StudySession studySession) {
        //studySession.setEndTime(LocalDateTime.now());

    }

    public List<StudySessionLog> getAllStudySessions(String userId) {
        return studySessionRepository.findAllByUserId(userId);
    }

    public Optional<StudySessionLog> getStudySessionById(String id) {
        return studySessionRepository.findById(id);
    }

    public void deleteStudySession(String id) {
        studySessionRepository.deleteById(id);
    }
}
