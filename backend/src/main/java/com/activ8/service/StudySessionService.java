package com.activ8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.FlashcardFlippedEvent;
import com.activ8.eventbus.events.StudySessionCompletedEvent;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class StudySessionService {

    @Autowired
    FlashcardService flashcardService;

    @Autowired
    StudySessionLogRepository studySessionRepository;

    @Autowired
    EventBus eventBus;

    @Autowired
    StudySessionManager studySessionManager;

    private static final Logger logger = LoggerFactory.getLogger(StudySessionService.class);


    @Transactional
    public void startFreeRoamStudySession(String userId, String studySetId) {
        try {
            if(studySessionManager.getSession(userId) == null) {
                studySessionManager.addSession(userId, new FreeRoamStudySession(studySetId, flashcardService));
            }
            studySessionManager.getSession(userId).start(studySetId);


            eventBus.publish(new StudySessionStartedEvent(studySessionManager.getSession(userId).toString(), userId, studySetId, LocalDateTime.now()));
        } catch (Exception e) {
            logger.error("Error starting FreeRoamStudySession for user {} with studySetId {}: {}", userId, studySetId, e.getMessage(), e);
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
        try {
            studySessionManager.getSession(userId).end();
            /*eventBus.publish(new StudySessionCompletedEvent(
                    studySessionManager.getSession(userId), 
                    userId, 
                    0, 
                    LocalDateTime.now()
                    
            ));*/

            studySessionManager.removeSession(userId);

        } catch (Exception e) {
            logger.error("Error ending FreeRoamStudySession for user {}: {}", userId, e.getMessage(), e);
        }
    }

    public void toggleFlashCardFlipped(String userId, String studySetId, String flashcardId) {
        eventBus.publish(new FlashcardFlippedEvent(userId, studySetId, flashcardId));
    }

    // Database related operations:
    public List<StudySessionLog> getAllStudySessionLogs(String userId) {
        return studySessionRepository.findAllByUserId(userId);
    }

    public Optional<StudySessionLog> getStudySessionLogById(String id) {
        return studySessionRepository.findById(id);
    }

    public StudySessionLog saveStudySessionLog(StudySessionLog studySessionLogToSave) {
        StudySessionLog savedStudySessionLog = studySessionRepository.save(studySessionLogToSave);
        return savedStudySessionLog;
    }
}
