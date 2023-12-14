package com.activ8.service;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.StudySessionProgressEvent;
import com.activ8.model.BasicPointsStrategy;
import com.activ8.model.PointsManager;
import com.activ8.model.StudySessionProgress;
import com.activ8.model.StudySessionProgressionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudySessionProgressionService {

    @Autowired
    StudySessionProgressionManager userProgressionManager;

    @Autowired
    WebSocketService webSocketService;

    @Autowired
    EventBus eventBus;

    private PointsManager pointsManager;

    public StudySessionProgressionService() {
        this.pointsManager = new PointsManager();
            // Choose the strategy for points algorithm - make this prettier in the future
            pointsManager.setStrategy(new BasicPointsStrategy());
    }

    public void handleCardFlip(String sessionId, String userId, String flashcardId, int studySetSize) {
        try {

            //Check if progress already exists
            if(userProgressionManager.getUserProgress(userId) == null) {
                userProgressionManager.addUserProgress(userId, new StudySessionProgress());
            }
            // Record card flip for the user progress
            userProgressionManager.getUserProgress(userId).recordFlashcardFlip(flashcardId);

            double progressionPercentage = pointsManager.calculateProgress(
                    userProgressionManager.getUserProgress(userId), 
                    studySetSize
            );

            eventBus.publish(new StudySessionProgressEvent(sessionId, userId, progressionPercentage));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void notifyProgressBar(String sessionId, double progress) {
        webSocketService.notifyProgressBar(sessionId, progress);
    }


}
