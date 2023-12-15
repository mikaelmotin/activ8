package com.activ8.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

/**
 * Controller managing study sets and related operations. This controller provides endpoints
 * for retrieving, creating, updating, and deleting study sets. Study sets are organized within
 * study folders and are associated with a user.
 *
 * Endpoints:
 * - GET /api/studysets/all/{studyFolderId}: Retrieve all study sets in a specific study folder.
 * - GET /api/studysets/{studySetId}: Retrieve a specific study set by its ID.
 * - POST /api/studysets: Create a new study set.
 * - PUT /api/studysets/{studySetId}: Update an existing study set.
 * - DELETE /api/studysets/{studySetId}: Delete a study set and its associated flashcards.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysets")
public class StudySetController {

  @Autowired
  StudySetService studySetService;


  /**
   * Used to get all StudySets in a StudyFolder by ID
   * 
   * @param studyFolderId
   * @return List<StudySet>
   */
  @GetMapping("/all/{studyFolderId}")
  public ResponseEntity<?> getStudySets(@PathVariable String studyFolderId) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyFolderId);

    return ResponseEntity.ok().body(studySets);
  }

  /**
   * Used to get a specific StudySet by its ID
   * 
   * @param studySetId
   * @return StudySet
   */
  @GetMapping("/{studySetId}")
  public ResponseEntity<?> getStudySet(@PathVariable String studySetId) {
    Optional<StudySet> studySet = studySetService.getStudySet(studySetId);

    return ResponseEntity.ok().body(studySet);
  }

  /**
   * Creates & returns the created StudySet
   * 
   * @param userDetails
   * @param studySetDTO
   * @return StudySet
   */
  @PostMapping
  public ResponseEntity<?> createStudySet(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody CreateStudySetDTO studySetDTO) {

    StudySet studySet = new StudySet(
        userDetails.getId(),
        studySetDTO.studyFolderId(),
        studySetDTO.title(),
        studySetDTO.description());

    StudySet createdStudySet = studySetService.saveStudySet(studySet);

    return ResponseEntity.ok().body(createdStudySet);
  }


  /**
   * Updates the values of a StudySet and returns the updated StudySet
   * 
   * @param userDetails
   * @param studySetId
   * @param updateStudySetDTO
   * @return StudySet
   */
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
        updateStudySetDTO.description());

    StudySet updatedStudySet = studySetService.saveStudySet(studySet);

    return ResponseEntity.ok().body(updatedStudySet);
  }

/**
 * Deletes a study set and its associated flashcards.
 * 
 * @param userDetails User details of the authenticated user.
 * @param studySetId ID of the study set to be deleted.
 * @return ResponseEntity indicating the success or failure of the deletion operation.
 */
  @DeleteMapping("/{studySetId}")
  public ResponseEntity<?> deleteStudySet(
          @AuthenticationPrincipal UserDetailsImpl userDetails,
          @PathVariable String studySetId) {

    if (studySetService.deleteStudySet(userDetails.getId(), studySetId)) {
      return ResponseEntity.ok().body("Study set " + studySetId + " and its flashcards successfully deleted");
    }

    return ResponseEntity.ok().body("Error while deleting study set " + studySetId);
  }

}
