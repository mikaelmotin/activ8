package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

public class PointsManager {

    private ConcurrentHashMap<String, StudySessionProgress> userPoints;

    public PointsManager() {
        this.userPoints = new ConcurrentHashMap<>();
    }

    //Calculate progress

    // public Integer calculateProgress() {

    // }
}
