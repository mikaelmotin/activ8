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

import com.activ8.model.Flashcard;
import com.activ8.model.SimpleFlashcard;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySetService;
import com.activ8.service.UserDetailsImpl;
import com.activ8.dto.CreateFlashcardDTO;
import com.activ8.dto.UpdateFlashcardDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

  @Autowired
  FlashcardService flashcardService;

  @Autowired
  StudySetService studySetService;

  /**
   * Retrieves all flashcards within a specified study set.
   *
   * @param studySetId The ID of the study set.
   * @return ResponseEntity containing a list of flashcards if retrieval is
   *         successful.
   */
  @GetMapping("/all/{studySetId}")
  public ResponseEntity<?> getAllFlashcardsInStudySet(@PathVariable String studySetId) {
    List<Flashcard> flashcards = flashcardService.getAllFlashcardsInStudySet(studySetId);

    return ResponseEntity.ok().body(flashcards);
  }

  @GetMapping("/{flashcardId}")
  public ResponseEntity<?> getFlashcard(@PathVariable String flashcardId) {
    Optional<Flashcard> flashcard = flashcardService.getFlashcard(flashcardId);

    return ResponseEntity.ok().body(flashcard);
  }

  /* BELOW ARE POST AND PUT REQUESTS FOR "SIMPLE FLASHCARDS" */
  // POST REQUESTS - Used to create data
  @PostMapping("/{studySetId}")
  public ResponseEntity<?> createFlashcard(@PathVariable String studySetId,
      @RequestBody CreateFlashcardDTO flashcardDTO) {
    Flashcard flashcard = new SimpleFlashcard(
          studySetId, flashcardDTO.term(), 
          flashcardDTO.definition(),
          flashcardDTO.difficulty()
    );
    Flashcard createdFlashcard = flashcardService.saveFlashcard(flashcard);

    return ResponseEntity.ok().body(createdFlashcard);
  }

  // PUT REQUESTS - Used to change data
  @PutMapping("/{flashcardId}")
  public ResponseEntity<?> updateFlashcard(
      @PathVariable String flashcardId,
      @RequestBody UpdateFlashcardDTO updateFlashcardDTO) {

    Flashcard flashcard = new SimpleFlashcard(
        flashcardId,
        updateFlashcardDTO.studySetId(),
        updateFlashcardDTO.term(),
        updateFlashcardDTO.definition(),
        updateFlashcardDTO.difficulty());

    Flashcard updatedFlashcard = flashcardService.saveFlashcard(flashcard);

    return ResponseEntity.ok().body(updatedFlashcard);
  }

  /* GENERAL DELETE REQUESTS */
  // DELETE REQUESTS - Used to remove data
  @DeleteMapping("/{flashcardId}")
  public ResponseEntity<?> deleteFlashcard(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable String flashcardId) {

    if (flashcardService.deleteFlashcard(userDetails.getId(), flashcardId)) {
      return ResponseEntity.ok().body("Flashcard " + flashcardId + " successfully deleted");
    }

    return ResponseEntity.ok().body("Error while deleting: " + flashcardId);
  }

}
