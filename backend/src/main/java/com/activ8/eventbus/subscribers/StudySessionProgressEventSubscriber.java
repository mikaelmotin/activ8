package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;

import com.activ8.eventbus.events.StudySessionProgressEvent;
import com.activ8.service.StudySessionProgressionService;


@Component
public class StudySessionProgressEventSubscriber implements Subscriber {
    
    @Autowired
    StudySessionProgressionService progressionService;

    private static final Logger logger = LoggerFactory.getLogger(StudySessionProgressEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof StudySessionProgressEvent progressEvent) { 
                System.out.println("StudySessionProgressEvent TOGGLED");
                progressionService.notifyProgressBar(progressEvent.sessionId(), progressEvent.progressionPercentage());
            }
            
        } catch (Exception e) {
            logger.error("Error handling StudySessionProgressEvent: {}", e.getMessage(), e);
        }
    }
}
