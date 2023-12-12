// package com.activ8.model;

// import com.activ8.eventbus.EventBus;
// import com.activ8.eventbus.events.Event;
// import com.activ8.eventbus.events.StudySessionCompletedEvent;
// import com.activ8.eventbus.events.StudySessionProgressEvent;
// import com.activ8.eventbus.events.StudySessionStartedEvent;
// import com.activ8.eventbus.subscribers.Subscriber;

// public class StudySessionManager implements Subscriber {

//     private StudySession currentStudySession;

//     public StudySessionManager(EventBus eventBus) {
//         eventBus.subscribe(this);
//     }

//     public void startStudySession(StudySession studySession) {
//         this.currentStudySession = studySession;
//         currentStudySession.start();
//     }

//     public void endStudySession() {
//         currentStudySession.end();
//         currentStudySession = null;
//     }

//     @Override
//     public void handleEvent(Event event) {
//         if (event instanceof StudySessionStartedEvent) {
//             // Handle the StudySessionStartedEvent, e.g., log it or perform additional actions
//             System.out.println("Study session started: " + ((StudySessionStartedEvent) event));
//         } else if (event instanceof StudySessionProgressEvent) {
//             // Handle the StudySessionProgressEvent, if needed
//         } else if (event instanceof StudySessionCompletedEvent) {
//             // Handle the StudySessionCompletedEvent, if needed
//         }
//         // Add more conditions for other events as necessary
//     }
// }

