package com.activ8.eventbus.events;

import java.time.LocalDateTime;

import com.activ8.model.StudySession;

public record StudySessionCompletedEvent(
    StudySession source,    
    String studySessionLogId,
    int numberOfCardsIteratedThrough,
    LocalDateTime endtime

) implements Event{
}
