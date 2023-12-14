package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

public class PointsManager {

    private ConcurrentHashMap<String, StudySessionProgress> userSessionProgressTracking;

    private PointsStrategy strategy;

    public PointsManager() {
        this.userSessionProgressTracking = new ConcurrentHashMap<>();
    }

    public void setStrategy(PointsStrategy strategy) {
        this.strategy = strategy;
    }

    //Calculate progress
    public double calculateProgress(StudySessionProgress sessionProgress, int studySetSize) {
        return strategy.calculateProgress(sessionProgress, studySetSize);
    }

    // Award the user points
    public void awardPoints(String userId, int points) {

    }
}
