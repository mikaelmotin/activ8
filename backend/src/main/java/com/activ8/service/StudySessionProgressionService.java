package com.activ8.service;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.PointLimitReachedEvent;
import com.activ8.eventbus.events.StudySessionProgressEvent;
import com.activ8.model.AdvancedPointsStrategy;
import com.activ8.model.BasicPointsStrategy;
import com.activ8.model.PointsManager;
import com.activ8.model.StudySessionLog;
import com.activ8.model.StudySessionProgress;
import com.activ8.model.StudySessionProgressionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudySessionProgressionService {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    StudySessionProgressionManager userProgressionManager;

    @Autowired
    WebSocketService webSocketService;

    @Autowired
    EventBus eventBus;

    private StudySessionLog sessionLog;

    private PointsManager pointsManager;

    public StudySessionProgressionService() {
        this.pointsManager = new PointsManager(userDetailsServiceImpl);
        // Default strategy for points algorithm 
        // Did not have time to implement a feature where the user can choose strategy
        // Uncomment the version you would like to try.
        //pointsManager.setStrategy(new BasicPointsStrategy());
        pointsManager.setStrategy(new AdvancedPointsStrategy(this));
    }


    public void tempSaveUserLog(StudySessionLog sessionLog) {
        this.sessionLog = sessionLog;
    }

    public StudySessionLog getSessionLog() {
        return sessionLog;
    }

    public void clearSessionLog() {
        this.sessionLog = null;
    }

    public void handleCardFlip(String sessionId, String userId, String flashcardId, int studySetSize) {
        try {

            // Check if progress already exists
            if (userProgressionManager.getUserProgress(userId) == null) {
                userProgressionManager.addUserProgress(userId, new StudySessionProgress());
            }
            // Record card flip for the user progress
            userProgressionManager.getUserProgress(userId).recordFlashcardFlip(flashcardId);


            double progressionPercentage = pointsManager.calculateProgress(
                    userProgressionManager.getUserProgress(userId),
                    studySetSize);

            eventBus.publish(new StudySessionProgressEvent(sessionId, userId, progressionPercentage));

            // Start award process if eligible
            if (isEligibleForPoints(progressionPercentage, userId)) {
                togglePointLimitReachedEvent(userId, progressionPercentage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCardIterated(String sessionId, String userId, int studySetSize) {
        try {
            // Check if progress already exists
            if (userProgressionManager.getUserProgress(userId) == null) {
                userProgressionManager.addUserProgress(userId, new StudySessionProgress());
            }
            // Record flashcard iterated for the user progress
            userProgressionManager.getUserProgress(userId).recordFlashcardIterated();


            double progressionPercentage = pointsManager.calculateProgress(
                    userProgressionManager.getUserProgress(userId),
                    studySetSize);

            eventBus.publish(new StudySessionProgressEvent(sessionId, userId, progressionPercentage));

            // Start award process if eligible
            if (isEligibleForPoints(progressionPercentage, userId)) {
                togglePointLimitReachedEvent(userId, progressionPercentage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isEligibleForPoints(double progressionPercentage, String userId) {
        if (progressionPercentage >= 1) {
            userProgressionManager.removeSession(userId);
            return true;
        }
        return false;
    }

    public void notifyProgressBar(String sessionId, double progress) {
        webSocketService.notifyProgressBar(sessionId, progress);
    }

    public void togglePointLimitReachedEvent(String userId, double pointPercentage) {
        try {
            eventBus.publish(new PointLimitReachedEvent(userId, pointPercentage));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
