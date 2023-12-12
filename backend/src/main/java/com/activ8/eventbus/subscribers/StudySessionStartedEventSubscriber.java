package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.StudySessionStartedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.model.StudySessionLog;
import com.activ8.repository.StudySessionLogRepository;
import com.activ8.service.FlashcardService;

@Component
public class StudySessionStartedEventSubscriber implements Subscriber {

    @Autowired
    FlashcardService flashcardService;

    @Autowired
    StudySessionLogRepository studySessionLogRepository;

    @Override
    public void handleEvent(Event event) {
        if (event instanceof StudySessionStartedEvent studySessionEvent) {
            StudySessionLog log = new StudySessionLog(
                studySessionEvent.userId(),
                studySessionEvent.source(),
                studySessionEvent.studySetId(),
                flashcardService.getAllFlashcardsInStudySet(studySessionEvent.studySetId()).size(),
                studySessionEvent.startTime()
            );

            studySessionLogRepository.save(log);
        }
    }
}
