package com.activ8.model;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.StudySessionCompletedEvent;
import com.activ8.eventbus.events.StudySessionStartedEvent;

public class FreeRoamStudySession implements StudySession {
    private EventBus eventBus;
    private boolean isStarted;

    public FreeRoamStudySession(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void start() {
        if (!isStarted) {
            isStarted = true;
            eventBus.publish(new StudySessionStartedEvent(this));
        }
        // Logic
    }

    @Override end() {
        // Cleanup logic
        // Log event for study session completion
        eventBus.post(new StudySessionCompletedEvent(this));

    }
}
