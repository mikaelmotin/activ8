package com.activ8.service;

import com.activ8.model.StudySessionProgress;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudySessionProgressionService {

    private Map<String, StudySessionProgress> userProgressMap = new HashMap<>();

    public void handleCardFlip(String userId) {
        StudySessionProgress progress = userProgressMap.getOrDefault(userId, new StudySessionProgress());
        progress.incrementCardFlips();
        userProgressMap.put(userId, progress);
        // Additional logic to update UI or database
    }

    public void finalizeSession(String userId) {
        StudySessionProgress progress = userProgressMap.getOrDefault(userId, new StudySessionProgress());
        progress.setCompleted(true);
        userProgressMap.put(userId, progress);
        // Additional logic for session completion
    }

    public void currentProgress() {
        // Init web socket to show progress bar
        // Should be triggered by an event that progression has been updated
    }

    // Getters, setters, and other methods...
}
