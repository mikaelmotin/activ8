package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.FlashcardFlippedEvent;
import com.activ8.service.StudySessionProgressionService;


@Component
public class FlashcardFlippedEventSubscriber implements Subscriber{

    @Autowired
    StudySessionProgressionService studySessionProgressionService;

    private static final Logger logger = LoggerFactory.getLogger(FlashcardFlippedEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof FlashcardFlippedEvent flashcardFlippedEvent) {
                // Logic
                // ...
                /*  Planning for tomorrow:
                    Here you should call the StudySessionProgressionService to notify that a card has been flipped.
                    The service should then keep track of this progress and it does so by using the StudySessionProgress class
                    The service should then call PointsManager to check the progression (This event combined with a FlashcardsIteratedEvent) and the PointsManager should return this
                    Then the progression should be sent through a WebSocket to update the UI progress bar
                    Once the progress is completed an event should happen that awards the user points accordingly.

                */                
                //studySessionProgressionService.updateCardFlipped(flashcardFlippedEvent.userId(), flashcardFlippedEvent.flashcardId());

            }
        } catch (Exception e) {
            logger.error("Error handling FlashcardFlippedEvent: {}", e.getMessage(), e);
        }

    }
    
}
