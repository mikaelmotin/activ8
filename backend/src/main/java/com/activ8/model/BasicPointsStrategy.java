package com.activ8.model;

/**
 * Basic implementation to calculate the progress of the study session.
 */
public class BasicPointsStrategy implements PointsStrategy {
    private static final double FLIPPED_WEIGHT = 0.2;   // Weight for flipped cards
    private static final double ITERATED_WEIGHT = 0.8;  // Weight for iterated cards

    @Override
    public double calculateProgress(StudySessionProgress sessionProgress, int studySetSize) {
        // In case studySetSize is < 1 - we don't want to get dividied by zero error
        if(studySetSize < 1) {
            studySetSize = 30;
        }

        double flashcardsFlippedMetric = (double) sessionProgress.getTotalFlips() / studySetSize;
        double flashcardsIteratedMetric = (double) sessionProgress.getnFlashcardsIterated() / studySetSize;

        // Calculate weighted average
        double weightedAverage = (flashcardsFlippedMetric * FLIPPED_WEIGHT) + (flashcardsIteratedMetric * ITERATED_WEIGHT);

        // Ensure that the result is between 0 and 1 because we are tracking in percentage.
        return Math.min(Math.max(weightedAverage, 0), 1);
    }
}


