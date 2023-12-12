package com.activ8.model;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.subscribers.Subscriber;

public class StudySessionManager implements Subscriber {

    private StudySession currentStudySession;

    public StudySessionManager(EventBus eventBus) {
        eventBus.subscribe(this);
    }

    public void startStudySession(StudySession studySession) {
        this.currentStudySession = studySession;
        currentStudySession.start();
    }

    public void endStudySession() {
        currentStudySession.end();
        currentStudySession = null;
    }

  

    @Override
    public void handleEvent(Event event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleEvent'");
    }


}
