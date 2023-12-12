package com.activ8.model;

public interface StudySession {
    void start(String userId);
    void end();
    void nextCard();
    // Other methods related to the study session
}

