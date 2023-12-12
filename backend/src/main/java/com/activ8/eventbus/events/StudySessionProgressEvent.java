package com.activ8.eventbus.events;


import com.activ8.model.StudySession;

public class StudySessionProgressEvent implements Event {
    public StudySession source;

    public StudySessionProgressEvent(StudySession source) {
        this.source = source;
    }
    
}
