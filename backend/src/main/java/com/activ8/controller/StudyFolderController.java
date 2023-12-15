package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.dto.CreateStudyFolderDTO;
import com.activ8.dto.UpdateStudyFolderDTO;
import com.activ8.model.StudyFolder;
import com.activ8.service.StudyFolderService;
import com.activ8.service.UserDetailsImpl;


/**
 * Controller responsible for handling operations related to study folders.
 * This controller supports the retrieval, creation, update, and deletion of study folders,
 * providing endpoints for managing user-specific study folders.
 * All endpoints are secured and require valid authentication.
 *
 * Endpoints:
 * - GET /api/studyfolders: Retrieves all study folders belonging to the authenticated user.
 * - GET /api/studyfolders/{studyFolderId}: Retrieves a specific study folder based on its ID.
 * - POST /api/studyfolders: Creates a new study folder for the authenticated user.
 * - PUT /api/studyfolders/{id}: Updates a specific study folder based on its ID.
 * - DELETE /api/studyfolders/{studyFolderId}: Deletes a specific study folder based on its ID.
 *
 * Request/Response DTOs:
 * - CreateStudyFolderDTO: DTO for creating a study folder with title and description.
 * - UpdateStudyFolderDTO: DTO for updating a study folder with new title and description.
 *
 * Security Features:
 * - Cross-Origin Resource Sharing (CORS) is enabled to allow requests from any origin.
 * - Endpoints are secured, and user details are obtained from the authentication principal.
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studyfolders")
public class StudyFolderController {

  @Autowired
  StudyFolderService studyFolderService;

/**
 * Used to retrieve all StudyFolders belonging to the user
 * @param userDetails
 * @return List<StudyFolder>
 */
  @GetMapping
  public ResponseEntity<?> getStudyFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    List<StudyFolder> studyFolders = studyFolderService.getAllStudyFolders(
        userDetails.getId()
    );

    return ResponseEntity.ok().body(studyFolders);
  }

/**
 * Retrieves a specific study folder based on its ID.
 *
 * @param studyFolderId The ID of the study folder.
 * @return ResponseEntity<StudyFolder> The requested study folder.
 */
  @GetMapping("/{studyFolderId}")
  public ResponseEntity<?> getStudyFolderById(@PathVariable String studyFolderId) {
      return studyFolderService.getStudyFolder(studyFolderId)
              .map(studyFolder -> ResponseEntity.ok().body(studyFolder))
              .orElse(ResponseEntity.notFound().build());
  }


/**
 * Creates a new study folder for the authenticated user.
 *
 * @param userDetails    The authenticated user's details.
 * @param studyFolderDTO DTO containing study folder information.
 * @return ResponseEntity<StudyFolder> The created study folder.
 */
  @PostMapping
  public ResponseEntity<?> createStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CreateStudyFolderDTO studyFolderDTO) {
    StudyFolder studyFolder = new StudyFolder(userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder createdFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(createdFolder);
  }


/**
 * Updates a specific study folder based on its ID.
 *
 * @param userDetails    The authenticated user's details.
 * @param studyFolderDTO DTO containing updated study folder information.
 * @param id             The ID of the study folder to update.
 * @return ResponseEntity<StudyFolder> The updated study folder.
 */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdateStudyFolderDTO studyFolderDTO, @PathVariable String id) {
    StudyFolder studyFolder = new StudyFolder(id, userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder updatedFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(updatedFolder);
  }

  /**
   * Deletes a specific study folder based on its ID.
   *
   * @param userDetails    The authenticated user's details.
   * @param studyFolderId  The ID of the study folder to delete.
   * @return ResponseEntity<String> Response message indicating success or failure.
   */
  @DeleteMapping("/{studyFolderId}")
  public ResponseEntity<?> deleteStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String studyFolderId) {
    
     if(studyFolderService.deleteStudyFolder(userDetails.getId(), studyFolderId)) {
       return ResponseEntity.ok().body("StudyFolder " + studyFolderId + " successfully deleted");
     }
    
     return ResponseEntity.ok().body("Error while deleting: " + studyFolderId);
  }
}
