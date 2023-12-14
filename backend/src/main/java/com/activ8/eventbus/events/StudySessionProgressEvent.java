package com.activ8.eventbus.events;


import com.activ8.model.StudySession;

public record StudySessionProgressEvent(
    String source,
    String userId,
    double progressionPercentage
) implements Event {

    
}
