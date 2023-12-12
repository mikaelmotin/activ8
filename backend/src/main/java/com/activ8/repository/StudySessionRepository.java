package com.activ8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.activ8.model.StudySession;

public interface StudySessionRepository extends MongoRepository<StudySession, String>{
    
    
}
