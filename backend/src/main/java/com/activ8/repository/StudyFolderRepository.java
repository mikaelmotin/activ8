package com.activ8.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.activ8.model.StudyFolder;

public interface StudyFolderRepository extends MongoRepository<StudyFolder, String> {
  List<StudyFolder> findAllByUserId(String userId);
  
  Optional<StudyFolder> findById(String id);

  void deleteById(String id);

}
