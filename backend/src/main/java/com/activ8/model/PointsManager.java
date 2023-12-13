package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

public class PointsManager {

    private ConcurrentHashMap<String, Integer> userPoints;

    public PointsManager() {
        this.userPoints = new ConcurrentHashMap<>();
    }

    /**
     * Adds points to a user's total.
     * @param userId The ID of the user.
     * @param points The number of points to add.
     */
    public void addPoints(String userId, int points) {
        userPoints.put(userId, userPoints.getOrDefault(userId, 0) + points);
    }

    /**
     * Retrieves the total points for a user.
     * @param userId The ID of the user.
     * @return The total points of the user.
     */
    public int getTotalPoints(String userId) {
        return userPoints.getOrDefault(userId, 0);
    }

    /**
     * Resets the points for a user.
     * @param userId The ID of the user.
     */
    public void resetPoints(String userId) {
        userPoints.put(userId, 0);
    }

    // Additional methods can be added to manage points based on different events or criteria
}
