package com.activ8.model;

import java.util.concurrent.ConcurrentHashMap;

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
    ConcurrentHashMap<String, Integer> flashcardStatistics,
    String startDate,
    String endDate,
    int timeSpentInMinutes
) {
    
}
