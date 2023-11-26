package com.activ8.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.activ8.model.Flashcard;

public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
  List<Flashcard> findAllByStudySetId(String studySetId);
  
  Optional<Flashcard> findById(String id);
  
}

