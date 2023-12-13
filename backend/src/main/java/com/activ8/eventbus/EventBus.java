package com.activ8.eventbus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList; // For thread-safe operations

import org.springframework.stereotype.Component;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.subscribers.Subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EventBus {
    private List<Subscriber> subscribers = new CopyOnWriteArrayList<>(); // Thread-safe list

    private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

    public void subscribe(Subscriber eventSubscriber) {
        subscribers.add(eventSubscriber);
    }

    public void unsubscribe(Subscriber eventSubscriber) {
        subscribers.remove(eventSubscriber);
    }

    public void publish(Event event) {
        for (Subscriber subscriber : subscribers) {
            try {
                logger.info("Publishing event to subscriber: {}", subscriber.getClass().getName());
                subscriber.handleEvent(event);
            } catch (Exception e) {
                logger.error("Error while handling event in subscriber {}: {}", subscriber.getClass().getName(), e.getMessage(), e);
            }
        }
    }
}
