package com.activ8.model;


/**
 * Strategy pattern for calculating the study session progress
 * you can choose different strategies in calculating
 * points and tracking progress. Once the certain threshold is reached,
 * the user is awarded points (in points manager).
 */
public interface PointsStrategy {
    double calculateProgress(StudySessionProgress sessionProgress, int studySetSize);
}
