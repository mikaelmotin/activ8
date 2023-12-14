package com.activ8.eventbus.events;

import java.time.LocalDateTime;

public record StudySessionCompletedEvent(
    String userId,
    String endtime

) implements Event{
}
