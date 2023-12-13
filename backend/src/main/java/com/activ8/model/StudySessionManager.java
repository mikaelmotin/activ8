package com.activ8.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StudySessionManager {
    private Map<String, StudySession> userSessions = new HashMap<>();

    public void addSession(String userId, StudySession session) {
        userSessions.put(userId, session);
    }

    public StudySession getSession(String userId) {
        return userSessions.get(userId);
    }

    public void removeSession(String userId) {
        userSessions.remove(userId);
    }

}
