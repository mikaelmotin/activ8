package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;

public class RightOrWrongEventSubscriber implements Subscriber {

    @Override
    public void handleEvent(Event event) {
        // Didn't have time to implement this feature
        throw new UnsupportedOperationException("Unimplemented method 'handleEvent'");
    }
    
}
