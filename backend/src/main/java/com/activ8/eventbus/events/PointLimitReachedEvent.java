package com.activ8.eventbus.events;

public record PointLimitReachedEvent (
    String userid,
    double pointPercentage
) implements Event {
    
}
