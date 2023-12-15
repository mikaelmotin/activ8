package com.activ8.eventbus.subscribers;

import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.Event;
import com.activ8.eventbus.events.StudySessionCompletedEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activ8.model.StudySessionLog;
import com.activ8.model.StudySessionProgress;
import com.activ8.model.StudySessionProgressionManager;
import com.activ8.service.StudySessionLogService;
import com.activ8.service.StudySessionProgressionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StudySessionCompletedEventSubscriber implements Subscriber {
    @Autowired
    StudySessionLogService studySessionLogService;

    @Autowired
    StudySessionProgressionService progressionService;

    @Autowired
    StudySessionProgressionManager sessionProgressionManager;

    @Autowired
    EventBus eventBus;

    private static final Logger logger = LoggerFactory.getLogger(StudySessionCompletedEventSubscriber.class);

    @Override
    public void handleEvent(Event event) {
        try {
            if (event instanceof StudySessionCompletedEvent studySessionCompletedEvent) {
                System.out.println("STUDYSESSIONCOMPLETED EVENT TOGGLED");

                StudySessionProgress sessionProgress = sessionProgressionManager
                        .getUserProgress(studySessionCompletedEvent.userId()
                );

                StudySessionLog unfinishedLog = progressionService.getSessionLog();

                // Define the date format
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                // Parse the start and end times to LocalDateTime objects
                LocalDateTime start = LocalDateTime.parse(unfinishedLog.startDate(), formatter);
                LocalDateTime end = LocalDateTime.parse(studySessionCompletedEvent.endtime(), formatter);

                // Calculate the duration between the start and end times
                Duration duration = Duration.between(start, end);

                int timeSpentInMinutes = (int) duration.toMinutes();

                int numberOfCardsIteratedThrough = sessionProgress.getTotalFlips();

                StudySessionLog logToSave = new StudySessionLog(
                        unfinishedLog.id(),
                        unfinishedLog.userId(),
                        unfinishedLog.studySessionType(),
                        unfinishedLog.studySetId(),
                        unfinishedLog.numberOfCards(),
                        numberOfCardsIteratedThrough,
                        sessionProgress.getAllFlipCounts(),
                        unfinishedLog.startDate(),
                        studySessionCompletedEvent.endtime().toString(),
                        timeSpentInMinutes);

                studySessionLogService.saveStudySessionLog(logToSave);
                progressionService.clearSessionLog();

                eventBus.unsubscribe(this);
            }

        } catch (Exception e) {
            logger.error("Error handling StudySessionCompletedEvent: {}", e.getMessage(), e);
        }

    }
}
