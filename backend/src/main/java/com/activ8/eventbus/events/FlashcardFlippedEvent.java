package com.activ8.eventbus.events;


public record FlashcardFlippedEvent(
    String userId,
    String flashcardId
) implements Event {

}
