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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studyfolders")
public class StudyFolderController {

  @Autowired
  StudyFolderService studyFolderService;


  // GET REQUESTS - Used to get data

  /**
   * Used to retrieve all StudyFolders belonging to the user
   * @param userDetails
   * @return List<StudyFolder>
   */
  @GetMapping
  public ResponseEntity<?> getStudyFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    List<StudyFolder> studyFolders = studyFolderService.getAllStudyFolders(userDetails.getId());

    return ResponseEntity.ok().body(studyFolders);
  }

  /**
   * Used to retrieve a specific StudyFolder by its ID
   * @param studyFolderId
   * @return StudyFolder
   */
  @GetMapping("/{studyFolderId}")
  public ResponseEntity<?> getStudyFolderById(@PathVariable String studyFolderId) {
      return studyFolderService.getStudyFolder(studyFolderId)
              .map(studyFolder -> ResponseEntity.ok().body(studyFolder))
              .orElse(ResponseEntity.notFound().build());
  }


  // POST REQUESTS - Used to create data

  /**
   * Creates & returns the created StudyFolder
   * @param userDetails
   * @param studyFolderDTO
   * @return StudyFolder
   */
  @PostMapping
  public ResponseEntity<?> createStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CreateStudyFolderDTO studyFolderDTO) {
    StudyFolder studyFolder = new StudyFolder(userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder createdFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(createdFolder);
  }


  // PUT REQUESTS - Used to change data

  /**
   * Updates the values of a StudyFolder and returns the updated StudyFolder
   * @param userDetails
   * @param studyFolderDTO
   * @param id
   * @return StudyFolder
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdateStudyFolderDTO studyFolderDTO, @PathVariable String id) {
    StudyFolder studyFolder = new StudyFolder(id, userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder updatedFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(updatedFolder);
  }

  // DELETE REQUESTS - Used to remove data

  /**
   * Delete StudyFolder by ID
   * @param userDetails
   * @param studyFolderId
   * @return message
   */
  @DeleteMapping("/{studyFolderId}")
  public ResponseEntity<?> deleteStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String studyFolderId) {
    
     if(studyFolderService.deleteStudyFolder(userDetails.getId(), studyFolderId)) {
       return ResponseEntity.ok().body("StudyFolder " + studyFolderId + " successfully deleted");
     }
     // IF I DELETE A STUDYFOLDER, SHOULD I DELETE ALL STUDY SETS IN THAT FOLDER?
    
     return ResponseEntity.ok().body("Error while deleting: " + studyFolderId);
  }

}
