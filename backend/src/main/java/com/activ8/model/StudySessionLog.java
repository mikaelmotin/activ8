package com.activ8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studysessionlogs")
public record StudySessionLog(
    @Id String id,
    String userId,
    String studySessionType,
    String studySetId,
    int numberOfCards,
    int numberOfCardsIteratedThrough,
    String startDate,
    String endDate,
    int timeSpentInMinutes
) {
    
}
