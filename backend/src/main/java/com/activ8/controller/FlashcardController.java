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


/**
 * Controller responsible for handling operations related to flashcards within study sets.
 * This controller supports the retrieval, creation, update, and deletion of flashcards,
 * as well as providing general methods for managing flashcards in a study set.
 * All endpoints are secured and require valid authentication.
 *
 * Endpoints:
 * - GET /api/flashcards/all/{studySetId}: Retrieves all flashcards within a specified study set.
 * - GET /api/flashcards/{flashcardId}: Retrieves a specific flashcard based on its ID.
 * - POST /api/flashcards/{studySetId}: Creates a new flashcard in the specified study set.
 * - PUT /api/flashcards/{flashcardId}: Updates a specific flashcard based on its ID.
 * - DELETE /api/flashcards/{flashcardId}: Deletes a specific flashcard based on its ID.
 *
 * All endpoints are secured and require valid user authentication.
 */
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


/**
 * Retrieves a specific flashcard based on its ID.
 *
 * @param flashcardId The ID of the flashcard.
 * @return ResponseEntity containing the flashcard if retrieval is successful.
 */
  @GetMapping("/{flashcardId}")
  public ResponseEntity<?> getFlashcard(@PathVariable String flashcardId) {
    Optional<Flashcard> flashcard = flashcardService.getFlashcard(flashcardId);

    return ResponseEntity.ok().body(flashcard);
  }

/**
 * Creates a new flashcard in the specified study set.
 *
 * @param studySetId   The ID of the study set.
 * @param flashcardDTO The data for creating the flashcard.
 * @return ResponseEntity containing the created flashcard if successful.
 */
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

/**
 * Updates a specific flashcard based on its ID.
 *
 * @param flashcardId       The ID of the flashcard.
 * @param updateFlashcardDTO The data for updating the flashcard.
 * @return ResponseEntity containing the updated flashcard if successful.
 */
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

/**
 * Deletes a specific flashcard based on its ID.
 *
 * @param userDetails The authenticated user details.
 * @param flashcardId  The ID of the flashcard.
 * @return ResponseEntity indicating the success or failure of the deletion.
 */
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
