package com.activ8.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


// Used to ease the use of the hashmap and in case you would like to implement additional logic
@Component
public class StudySessionProgressionManager {
    private Map<String, StudySessionProgress> userSessionProgress = new HashMap<>();

    public void addUserProgress(String userId, StudySessionProgress progress) {
        userSessionProgress.put(userId, progress);
    }

    public StudySessionProgress getUserProgress(String userId) {
        return userSessionProgress.get(userId);
    }

    public void removeSession(String userId) {
        userSessionProgress.remove(userId);
    }
    
}
