package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.StudySessionCompletedEvent;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import com.activ8.model.StudySessionLog;
import com.activ8.repository.StudySessionLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudySessionCompletedEventSubscriber implements Subscriber {

    @Autowired
    private StudySessionLogRepository studySessionLogRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudySessionCompletedEventSubscriber.class);


    @Override
    public void handleEvent(Event event) {
    //     try {
    //         if (event instanceof StudySessionCompletedEvent completedEvent) {
    //             StudySessionLog log = studySessionLogRepository.findById(completedEvent.studySessionLogId())
    //                     .orElse(null);

    //             if (log != null) {
    //                 log.setNumberOfCardsIteratedThrough(completedEvent.numberOfCardsIteratedThrough());
    //                 long minutesSpent = Duration.between(log.getStartDate(), completedEvent.endtime()).toMinutes();
    //                 log.setTimeSpentInMinutes((int) minutesSpent);
    //                 log.setEndDate(completedEvent.endtime()); // Assuming you have a setter for endDate

    //                 studySessionLogRepository.save(log);
    //             }
    //         }

    //     } catch (Exception e) {
    //         logger.error("Error handling StudySessionCompletedEvent: {}", e.getMessage(), e);
    //     }
        System.out.println("STUDYSESSIONCOMPLETED EVENT TOGGLED");
    }
}
