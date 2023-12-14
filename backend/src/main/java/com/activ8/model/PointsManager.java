package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.service.UserDetailsServiceImpl;

public class PointsManager {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    private ConcurrentHashMap<String, StudySessionProgress> userSessionProgressTracking;

    private static final int pointMultiplier = 100;

    private PointsStrategy strategy;

    public PointsManager(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userSessionProgressTracking = new ConcurrentHashMap<>();
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    public void setStrategy(PointsStrategy strategy) {
        this.strategy = strategy;
    }

    //Calculate progress
    public double calculateProgress(StudySessionProgress sessionProgress, int studySetSize) {
        return strategy.calculateProgress(sessionProgress, studySetSize);
    }

    // Award the user points
    public void awardPoints(String userId, double pointPercentage) {
        int pointsToBeAwarded = (int) pointPercentage * pointMultiplier;

        userDetailsServiceImpl.awardPoints(userId, pointsToBeAwarded);
    }
}
