package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.StudySessionCompletedEvent;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import com.activ8.model.StudySessionLog;
import com.activ8.repository.StudySessionLogRepository;

public class StudySessionCompletedEventSubscriber implements Subscriber {

    @Autowired
    private StudySessionLogRepository studySessionLogRepository;

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof StudySessionCompletedEvent completedEvent) {
                StudySessionLog log = studySessionLogRepository.findById(completedEvent.studySessionLogId())
                        .orElse(null);

                if (log != null) {
                    log.setNumberOfCardsIteratedThrough(completedEvent.numberOfCardsIteratedThrough());
                    long minutesSpent = Duration.between(log.getStartDate(), completedEvent.endtime()).toMinutes();
                    log.setTimeSpentInMinutes((int) minutesSpent);
                    log.setEndDate(completedEvent.endtime()); // Assuming you have a setter for endDate

                    studySessionLogRepository.save(log);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
