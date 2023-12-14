package com.activ8.eventbus.events;


public record FlashcardFlippedEvent(
    String sessionId,
    String userId,
    String studySetId,
    String flashcardId
) implements Event {

}
