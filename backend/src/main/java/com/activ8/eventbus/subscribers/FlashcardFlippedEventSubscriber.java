package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.FlashcardFlippedEvent;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySessionProgressionService;


@Component
public class FlashcardFlippedEventSubscriber implements Subscriber{

    @Autowired
    StudySessionProgressionService studySessionProgressionService;

    @Autowired
    FlashcardService flashcardService;

    private static final Logger logger = LoggerFactory.getLogger(FlashcardFlippedEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            
            if (event instanceof FlashcardFlippedEvent flashcardFlippedEvent) {
                System.out.println("FlashcardFlippedEvent TOGGLED");
                int studySetSize = flashcardService.getAllFlashcardsInStudySet(flashcardFlippedEvent.studySetId()).size();
                studySessionProgressionService.handleCardFlip(
                        flashcardFlippedEvent.sessionId(), 
                        flashcardFlippedEvent.userId(), 
                        flashcardFlippedEvent.flashcardId(), 
                        studySetSize
                );
            }
        } catch (Exception e) {
            logger.error("Error handling FlashcardFlippedEvent: {}", e.getMessage(), e);
        }

    }
    
}
