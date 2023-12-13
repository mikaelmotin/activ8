package com.activ8.eventbus.events;

import java.time.LocalDateTime;

import com.activ8.model.StudySession;

public record StudySessionStartedEvent(
    StudySession source,
    String userId,
    String studySetId,
    LocalDateTime startTime
) implements Event {

}
