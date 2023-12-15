package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.FlashcardIteratedEvent;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySessionProgressionService;
import com.activ8.service.StudySessionService;

@Component
public class FlashcardIteratedEventSubscriber implements Subscriber {

    @Autowired
    StudySessionProgressionService studySessionProgressionService;

    @Autowired
    FlashcardService flashcardService;

    private static final Logger logger = LoggerFactory.getLogger(FlashcardIteratedEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof FlashcardIteratedEvent flashcardIteratedEvent) {
                System.out.println("FlashcardIteratedEvent TOGGLED");
                
                int studySetSize = flashcardService.getAllFlashcardsInStudySet(flashcardIteratedEvent.studySetId()).size();

                studySessionProgressionService.handleCardIterated(flashcardIteratedEvent.sessionId(), flashcardIteratedEvent.userId(), studySetSize);

            }
        } catch (Exception e) {
            logger.error("Error handling FlashcardIteratedEvent: {}", e.getMessage(), e);
        }

    }
}
