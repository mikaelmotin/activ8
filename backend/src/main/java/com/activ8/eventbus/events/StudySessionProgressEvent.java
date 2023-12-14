package com.activ8.eventbus.events;


public record StudySessionProgressEvent(
    String sessionId,
    String userId,
    double progressionPercentage
) implements Event {

    
}
