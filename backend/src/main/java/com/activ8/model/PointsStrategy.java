package com.activ8.model;


/**
 * Strategy pattern for calculating the study session progress
 * this is good because you can choose different strategies in calculating
 * points and tracking progress. Once the certain threshold is reached.
 * The user is awarded points (happens in points manager).
 */
public interface PointsStrategy {
    double calculateProgress(StudySessionProgress sessionProgress, int studySetSize);
}
