package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.dto.CreateStudySetDTO;
import com.activ8.model.StudySet;
import com.activ8.service.StudySetService;
import com.activ8.service.UserDetailsImpl;
import com.activ8.dto.UpdateStudySetDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysets")
public class StudySetController {

  @Autowired
  StudySetService studySetService;

  // POST REQUESTS - Used to create data
  @PostMapping
  public ResponseEntity<?> createStudySet(@AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody CreateStudySetDTO studySetDTO) {
    StudySet studySet = new StudySet(userDetails.getId(), studySetDTO.studyFolderId(), studySetDTO.title(),
        studySetDTO.description());
    StudySet createdStudySet = studySetService.saveStudySet(studySet);

    return ResponseEntity.ok().body(createdStudySet);
  }

  // GET REQUESTS - Used to get data
  @GetMapping("/{studyFolderId}")
  public ResponseEntity<?> getStudySets(@PathVariable String studyFolderId) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyFolderId);

    return ResponseEntity.ok().body(studySets);
  }

  @GetMapping("/{studyFolderId}/shares")
  public ResponseEntity<?> getSharedStudySets(@PathVariable String studyFolderId) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyFolderId);

    return ResponseEntity.ok().body(studySets);
  }

  // PUT REQUESTS - Used to change data
  @PutMapping("/{studySetId}")
  public ResponseEntity<?> updateStudySet(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable String studySetId,
      @RequestBody UpdateStudySetDTO updateStudySetDTO) {

    StudySet studySet = new StudySet(
        studySetId,
        userDetails.getId(),
        updateStudySetDTO.studyFolderId(),
        updateStudySetDTO.title(),
        updateStudySetDTO.description()
    );

    StudySet updatedStudySet = studySetService.saveStudySet(studySet);

    return ResponseEntity.ok().body(updatedStudySet);
  }

}
