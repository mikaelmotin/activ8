package com.activ8.eventbus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList; // For thread-safe operations
import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.subscribers.Subscriber;

public class EventBus {
    private List<Subscriber> subscribers = new CopyOnWriteArrayList<>(); // Thread-safe list

    public void subscribe(Subscriber eventSubscriber) {
        subscribers.add(eventSubscriber);
    }

    public void unsubscribe(Subscriber eventSubscriber) {
        subscribers.remove(eventSubscriber);
    }

    public void publish(Event event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.handleEvent(event);
        }
    }
}
