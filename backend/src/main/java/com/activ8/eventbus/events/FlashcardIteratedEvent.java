package com.activ8.eventbus.events;

public record FlashcardIteratedEvent(
    String sessionId,
    String userId,
    String studySetId
) implements Event {
    
}
