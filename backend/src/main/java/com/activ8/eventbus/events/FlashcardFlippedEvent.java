package com.activ8.eventbus.events;

import com.activ8.model.StudySession;

public class FlashcardFlippedEvent implements Event {
    public StudySession source;

    public FlashcardFlippedEvent(StudySession source) {
        this.source = source;
    }
    // Do something
}
