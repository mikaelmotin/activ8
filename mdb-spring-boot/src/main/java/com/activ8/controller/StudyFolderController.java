package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.activ8.security.service.StudyFolderService;
import com.activ8.security.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studyfolders")
public class StudyFolderController {

  @Autowired
  StudyFolderService studyFolderService;

  /**
   * 
   * @return List of all study folders
   */
  // @GetMapping
  // public ResponseEntity<?> getStudyFolders(@AuthenticationPrincipal
  // UserDetailsImpl userDetails) {
  // System.out.println("########### " + userDetails.getId());
  // var folders = new ArrayList<StudyFolder>();
  // folders.add(new StudyFolder("id1", "6327864237824", "apa" , "test desc"));

  // return ResponseEntity.ok().body(folders);
  // }

  @PostMapping
  public ResponseEntity<?> createStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CreateStudyFolderDTO studyFolderDTO) {
    StudyFolder studyFolder = new StudyFolder(userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder createdFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(createdFolder);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateStudyFolder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdateStudyFolderDTO studyFolderDTO, @PathVariable String id) {
    StudyFolder studyFolder = new StudyFolder(id, userDetails.getId(), studyFolderDTO.title(), studyFolderDTO.description());
    StudyFolder updatedFolder = studyFolderService.saveStudyFolder(studyFolder);
    

    return ResponseEntity.ok().body(updatedFolder);
  }

  @GetMapping
  public ResponseEntity<?> getStudyFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    List<StudyFolder> studyFolders = studyFolderService.getAllStudyFolders(userDetails.getId());

    return ResponseEntity.ok().body(studyFolders);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getStudyFolderById(@PathVariable String id) {
      return studyFolderService.getStudyFolder(id)
              .map(studyFolder -> ResponseEntity.ok().body(studyFolder))
              .orElse(ResponseEntity.notFound().build());
  }
  
}
