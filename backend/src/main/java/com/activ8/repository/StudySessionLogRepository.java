package com.activ8.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.activ8.model.StudySessionLog;

public interface StudySessionLogRepository extends MongoRepository<StudySessionLog, String>{
  List<StudySessionLog> findAllByUserId(String userId);
  
  Optional<StudySessionLog> findById(String id);
    
}
