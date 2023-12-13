package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.FlashcardFlippedEvent;


@Component
public class FlashcardFlippedEventSubscriber implements Subscriber{

     private static final Logger logger = LoggerFactory.getLogger(FlashcardFlippedEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof FlashcardFlippedEvent flashcardFlippedEvent) {
                // Logic
                // ...
                
                //progressionService.updateCardFlipped(flashcardFlippedEvent.userId(), flashcardFlippedEvent.flashcardId());

            }
        } catch (Exception e) {
            logger.error("Error handling FlashcardFlippedEvent: {}", e.getMessage(), e);
        }

    }
    
}
