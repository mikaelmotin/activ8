package com.activ8.eventbus.events;

import java.time.LocalDateTime;

import com.activ8.model.StudySession;

public record StudySessionStartedEvent(
    String source,
    String userId,
    String studySetId,
    LocalDateTime startTime
) implements Event {

}
