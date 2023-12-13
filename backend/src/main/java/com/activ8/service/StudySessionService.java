package com.activ8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.StudySessionStartedEvent;
import com.activ8.model.EDifficulty;
import com.activ8.model.Flashcard;
import com.activ8.model.FreeRoamStudySession;
import com.activ8.model.StudySession;
import com.activ8.model.StudySessionLog;
import com.activ8.model.StudySessionManager;
import com.activ8.repository.StudySessionLogRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudySessionService {

    @Autowired
    private FlashcardService flashcardService;

    @Autowired
    private StudySessionLogRepository studySessionRepository;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private StudySessionManager studySessionManager;

    private StudySession studySession;

    @Transactional
    public void startFreeRoamStudySession(String userId, String studySetId) {
        try {
            // if(studySessionManager.getSession(userId) != null) {
                studySessionManager.addSession(userId, new FreeRoamStudySession(studySetId, flashcardService));
                
            //}
            studySessionManager.getSession(userId).start(studySetId);
            //eventBus.publish(new StudySessionStartedEvent(studySession, userId, studySetId, LocalDateTime.now()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public Flashcard nextCard(String userId) {
        return studySessionManager.getSession(userId).nextCard();
    }

    public void assignDifficultyToFlashcard(String userId, String flashcardId, EDifficulty difficulty) {
        Optional<Flashcard> flashcardToBeAssigned = flashcardService.getFlashcard(flashcardId);

        studySessionManager.getSession(userId).assignDifficulty(flashcardToBeAssigned.get(), difficulty);
    }

    public void endStudySession(String userId) {
        studySessionManager.getSession(userId).end();
        studySessionManager.removeSession(userId);
    }

    // Database related operations:
    public List<StudySessionLog> getAllStudySessionLogs(String userId) {
        return studySessionRepository.findAllByUserId(userId);
    }

    public Optional<StudySessionLog> getStudySessionLogById(String id) {
        return studySessionRepository.findById(id);
    }
}
