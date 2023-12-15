package com.activ8.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.activ8.service.StudySessionProgressionService;

/**
 * An advanced implementation to calculate the points progression of the study
 * session
 * considering attention decay over time.
 * With the current DECAY_RATE, a User is given no more points after a 30 minute session
 */

public class AdvancedPointsStrategy implements PointsStrategy {    
    private StudySessionProgressionService progressionService;

    private static final double FLIPPED_WEIGHT = 0.2; // Weight for flipped cards
    private static final double ITERATED_WEIGHT = 0.8; // Weight for iterated cards
    private static final int MINIMUM_SIZE_WEIGHT = 25; // Weight for minimum size, we don't want to give too many points
                                                       // for a small deck.
    private static final double DECAY_RATE = 0.02; // Attention decay rate per minute

    public AdvancedPointsStrategy(StudySessionProgressionService progressionService) {
        this.progressionService = progressionService;
    }

    @Override
    public double calculateProgress(StudySessionProgress sessionProgress, int studySetSize) {
 
        // In case studySetSize is < MINIMUM_SIZE_WEIGHT, set it to MINIMUM_SIZE_WEIGHT
        int sizeWeight = Math.max(studySetSize, MINIMUM_SIZE_WEIGHT);

        double flashcardsFlippedMetric = (double) sessionProgress.getTotalFlips() / sizeWeight;
        double flashcardsIteratedMetric = (double) sessionProgress.getnFlashcardsIterated() / sizeWeight;

        // Calculate the attention decay factor based on session duration
        double sessionDurationInMinutes = calculateElapsedTime();
        double attentionDecay = Math.exp(-DECAY_RATE * sessionDurationInMinutes);

        // Apply attention decay to the metrics
        // flashcardsFlippedMetric *= attentionDecay;
        // flashcardsIteratedMetric *= attentionDecay;

        // Calculate weighted average
        double weightedAverage = (flashcardsFlippedMetric * FLIPPED_WEIGHT)
                + (flashcardsIteratedMetric * ITERATED_WEIGHT) * attentionDecay;

        // Ensure that the result is between 0 and 1 because we are tracking in
        // percentage.
        return Math.min(Math.max(weightedAverage, 0), 1);
    }

    /**
     * Calculate the elapsed time in minutes
     * 
     * @return double <-> elapsed time in minutes
     */
    private double calculateElapsedTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            LocalDateTime startTime = LocalDateTime.parse(progressionService.getSessionLog().startDate(), formatter);

            LocalDateTime currentTime = LocalDateTime.now();

            double elapsedMinutes = Duration.between(startTime, currentTime).toMinutes();

            return elapsedMinutes;
        } catch (Exception e) {
            e.printStackTrace();
            return MINIMUM_SIZE_WEIGHT; // default to this 
        }

    }
}
