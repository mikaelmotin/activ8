package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.dto.CreateStudySetDTO;
import com.activ8.model.StudySet;
import com.activ8.security.service.StudySetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysets")
public class StudySetController {

  @Autowired
  StudySetService studySetService;


  // POST REQUESTS
  @PostMapping
  public ResponseEntity<?> createStudySet(@RequestBody CreateStudySetDTO studySetDTO) {
    StudySet studySet = new StudySet(studySetDTO.studyFolderId(), studySetDTO.title(), studySetDTO.description());
    StudySet createdStudySet = studySetService.saveStudySet(studySet);

    return ResponseEntity.ok().body(createdStudySet);
  }
  

  // GET REQUESTS
  @GetMapping("/{studyfolderid}")
  public ResponseEntity<?> getStudySets(@PathVariable String studyfolderid) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyfolderid);

    
    return ResponseEntity.ok().body(studySets);
  }

  
  // GET REQUESTS
  @GetMapping("/{studyfolderid}/shares")
  public ResponseEntity<?> getSharedStudySets(@PathVariable String studyfolderid) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyfolderid);

    
    return ResponseEntity.ok().body(studySets);
  }
}
