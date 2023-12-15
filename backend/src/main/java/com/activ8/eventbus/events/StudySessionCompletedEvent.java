package com.activ8.eventbus.events;


public record StudySessionCompletedEvent(
    String userId,
    String endtime

) implements Event{
}
