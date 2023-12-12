package com.activ8.eventbus;

import java.util.ArrayList;
import java.util.List;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.subscribers.Subscriber;

public class EventBus {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber eventSubscriber) {

    }

    public void unsubscribe(Subscriber eventSubscriber) {

    }

    public void publish(Event event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.handleEvent(event);
        }
    }

}
