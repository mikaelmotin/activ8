package com.activ8.eventbus.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.PointLimitReachedEvent;
import com.activ8.model.PointsManager;
import com.activ8.service.StudySessionProgressionService;
import com.activ8.service.UserDetailsServiceImpl;

@Component
public class PointLimitReachedEventSubscriber implements Subscriber {
    @Autowired
    StudySessionProgressionService studySessionProgressionService;

    @Autowired
    EventBus eventBus;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    private PointsManager pointsManager;

    private static final Logger logger = LoggerFactory.getLogger(PointLimitReachedEventSubscriber.class);
    

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof PointLimitReachedEvent pointLimitReachedEvent) {
                System.out.println("PointLimitReachedEvent TOGGLED");

                pointsManager = new PointsManager(userDetailsServiceImpl);
                pointsManager.awardPoints(pointLimitReachedEvent.userid(), pointLimitReachedEvent.pointPercentage());
                eventBus.unsubscribe(this);
            }
        } catch (Exception e) {
            logger.error("Error handling PointLimitReachedEvent: {}", e.getMessage(), e);
        }

    }
}
