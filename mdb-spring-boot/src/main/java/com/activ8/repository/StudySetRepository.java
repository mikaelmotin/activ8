package com.activ8.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.activ8.model.StudySet;

public interface StudySetRepository extends MongoRepository<StudySet, String> {
  List<StudySet> findAllByStudyFolderId(String studyFolderId);
  
  Optional<StudySet> findById(String id);

  
}

