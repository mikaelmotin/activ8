package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;

public interface Subscriber {
    void handleEvent(Event event);
}

