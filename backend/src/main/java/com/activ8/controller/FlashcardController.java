package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activ8.dto.UpdateStudySetDTO;
import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import com.activ8.model.StudySet;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySetService;
import com.activ8.dto.CreateFlashcardDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

  @Autowired
  FlashcardService flashcardService;

   @Autowired
  StudySetService studySetService;


  // POST REQUESTS - Used to create data
  @PostMapping("/{studySetId}")
  public ResponseEntity<?> createFlashcard(@PathVariable String studySetId, @RequestBody CreateFlashcardDTO flashcardDTO) {
    Flashcard flashcard = new SimpleFlashcard(studySetId, flashcardDTO.term(), flashcardDTO.definition(),flashcardDTO.difficulty());
    Flashcard createdFlashcard = flashcardService.saveFlashcard(flashcard);


    return ResponseEntity.ok().body(createdFlashcard);
  }
  

  // GET REQUESTS - Used to get data
  @GetMapping("/{studySetId}")
  public ResponseEntity<?> getFlashcards(@PathVariable String studySetId) {
    List<Flashcard> flashcards = flashcardService.getAllFlashcardsByStudySetId(studySetId);

    
    return ResponseEntity.ok().body(flashcards);
  }

  
  @GetMapping("/{studyfolderid}/shares")
  public ResponseEntity<?> getSharedStudySets(@PathVariable String studyfolderid) {
    List<StudySet> studySets = studySetService.getAllStudySets(studyfolderid);

    
    return ResponseEntity.ok().body(studySets);
  }


  // PUT REQUESTS - Used to change data
  @PutMapping("/{id}")
  public ResponseEntity<?> updateFlashcard(@PathVariable String id, @RequestBody UpdateStudySetDTO updateStudySetDTO) {
    StudySet studySet= new StudySet(id, updateStudySetDTO.studyFolderId(), null, updateStudySetDTO.title(), updateStudySetDTO.description());
    StudySet updatedStudySet = studySetService.saveStudySet(studySet);
   

    return ResponseEntity.ok().body(updatedStudySet);
  }




}

