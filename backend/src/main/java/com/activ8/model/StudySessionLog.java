package com.activ8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "studysessionlogs")
public class StudySessionLog {
    @Id
    String id;
    private String userId;
    private StudySession studySessionType;
    private String studySetId;
    private int numberOfCards;
    private int numberOfCardsIteratedThrough;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int timeSpentInMinutes;

  
    public StudySessionLog(String userId, StudySession studySessionType, String studySetId, int numberOfCards, LocalDateTime startDate) {
        this.userId = userId;
        this.studySessionType = studySessionType;
        this.studySetId = studySetId;
        this.numberOfCards = numberOfCards;
        this.numberOfCardsIteratedThrough = 0;
        this.startDate = startDate;
        this.endDate = null;
        this.timeSpentInMinutes = 0;
    }

 
    public int getTimeSpentInMinutes() {
        return timeSpentInMinutes;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setTimeSpentInMinutes(int timeSpentInMinutes) {
        this.timeSpentInMinutes = timeSpentInMinutes;
    }

    public void setNumberOfCardsIteratedThrough(int numberOfCardsIteratedThrough) {
        this.numberOfCardsIteratedThrough = numberOfCardsIteratedThrough;
    }
}
