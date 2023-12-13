package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.StudySessionStartedEvent;
import com.activ8.model.StudySessionLog;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.activ8.service.FlashcardService;
import com.activ8.service.StudySessionService;

@Component
public class StudySessionStartedEventSubscriber implements Subscriber {

    @Autowired
    FlashcardService flashcardService;

    @Autowired
    StudySessionService studySessionService;

    private static final Logger logger = LoggerFactory.getLogger(StudySessionCompletedEventSubscriber.class);

    @Transactional
    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof StudySessionStartedEvent studySessionEvent) {
                System.out.println("STUDYSESSION STARTED EVENT SUBSCRIBER HAS BEEN ACTIVATED");

                int nFlashcards;

                try {
                    nFlashcards = flashcardService.getAllFlashcardsInStudySet(studySessionEvent.studySetId()).size();
                } catch (Exception e) {
                    nFlashcards = -401;
                }

                StudySessionLog log = new StudySessionLog(
                        null, 
                        studySessionEvent.userId(), 
                        studySessionEvent.source(), 
                        studySessionEvent.studySetId(), 
                        nFlashcards, 
                        0, 
                        LocalDateTime.now().toString(), 
                        "To Be Decided", 
                        0
                );
                
               studySessionService.saveStudySessionLog(log);
            }

        } catch (Exception e) {
            logger.error("Error handling StudySessionStartedEvent: {}", e.getMessage(), e);
        }
    }
}
