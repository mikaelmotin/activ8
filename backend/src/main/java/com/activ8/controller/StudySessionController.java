package com.activ8.controller;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.activ8.dto.FlashcardDifficultyDTO;
import com.activ8.dto.FlashcardFlippedDTO;
import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.subscribers.StudySessionProgressEventSubscriber;
import com.activ8.model.Flashcard;
import com.activ8.model.StudySessionLog;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySessionLogService;
import com.activ8.service.StudySessionService;
import com.activ8.service.UserDetailsImpl;


/**
 * Controller handling study sessions and related operations. This controller provides endpoints
 * for starting, managing, and retrieving information about study sessions, as well as operations
 * such as assigning difficulty to flashcards and flipping flashcards during a session.
 *
 * Endpoints:
 * - GET /api/studysessions: Retrieve all study sessions associated with the authenticated user.
 * - GET /api/studysessions/{studySessionId}: Retrieve a specific study session by its ID.
 * - GET /api/studysessions/nextCard/{studySetId}/{sessionId}: Retrieve the next flashcard for a study session.
 * - POST /api/studysessions/start/{studySetId}: Start a new study session for the given study set.
 * - POST /api/studysessions/assignDifficulty/{flashcardId}: Assign difficulty to a flashcard during a study session.
 * - POST /api/studysessions/endSession: End the active study session for the user.
 * - POST /api/studysessions/flipCard: Flip a flashcard during a study session.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysessions")
public class StudySessionController {

    @Autowired
    StudySessionService studySessionService;

    @Autowired
    StudySessionLogService studySessionLogService;

    @Autowired
    FlashcardService flashcardService;

    @Autowired
    EventBus eventBus;

    @Autowired
    StudySessionProgressEventSubscriber sessionProgressEventSubscriber;

    
    /**
     * Retrieves all study sessions associated with the authenticated user.
     *
     * @param userDetails Authenticated user details.
     * @return ResponseEntity<List<StudySessionLog>> List of study session logs.
     */
    @GetMapping
    public ResponseEntity<List<StudySessionLog>> getStudySessions(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StudySessionLog> studySessions = studySessionLogService.getAllStudySessionLogs(userDetails.getId());
        return ResponseEntity.ok(studySessions);
    }

    /**
     * Retrieves a specific study session by its ID.
     *
     * @param studySessionId ID of the study session.
     * @return ResponseEntity<?> The requested study session.
     */
    @GetMapping("/{studySessionId}")
    public ResponseEntity<?> getStudySessionById(@PathVariable String studySessionId) {
        return studySessionLogService.getStudySessionLogById(studySessionId)
                .map(studySession -> ResponseEntity.ok().body(studySession))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the next flashcard for a study session.
     *
     * @param userDetails Authenticated user details.
     * @param studySetId  ID of the study set.
     * @param sessionId   ID of the study session.
     * @return Message Error if Failed 
     */
    @GetMapping("/nextCard/{studySetId}/{sessionId}") 
    public ResponseEntity<?> nextCard(
            @AuthenticationPrincipal UserDetailsImpl userDetails, 
            @PathVariable String studySetId, 
            @PathVariable String sessionId 
        ) {
            try {
                Flashcard flashcard = studySessionService.nextCard(
                        userDetails.getId(),  
                        sessionId,
                        studySetId
                );


                return ResponseEntity.ok().body(flashcard);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Couldn't deliver next card");
            }
    }

     /**
     * Starts a new study session.
     *
     * @param userDetails Authenticated user details.
     * @param studySetId  ID of the study set.
     * @return Response indicating success or failure.
     */
    @PostMapping("/start/{studySetId}")
    public ResponseEntity<?> startStudySession(@AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String studySetId) {
        try {
            studySessionService.startFreeRoamStudySession(userDetails.getId(), studySetId);
            return ResponseEntity.ok("startSession success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("startSession failed");
        }

    }

    /**
     * Assigns difficulty to a flashcard.
     *
     * @param userDetails            Authenticated user details.
     * @param flashcardId            ID of the flashcard.
     * @param flashcardDifficultyDTO DTO containing flashcard difficulty information.
     * @return Response indicating success or failure.
     */
    @PostMapping("/assignDifficulty/{flashcardId}")
    public ResponseEntity<?> assignFlashcardDifficulty(@AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String flashcardId, @RequestBody FlashcardDifficultyDTO flashcardDifficultyDTO) {
        try {
            if(flashcardService.existsByFlashcardId(flashcardId)) {
                studySessionService.assignDifficultyToFlashcard(
                        userDetails.getId(),
                        flashcardId,
                        flashcardDifficultyDTO.getDifficultyEnum());

                return ResponseEntity.ok("assigned difficulty to flashcard successfully");
            } else {
                throw new NameNotFoundException(flashcardId + " is not a flashcard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("assign difficulty to flashcard failed");
        }
    }

    /**
     * Ends the active studysession for the user
     * 
     * @param userDetails User details of the authenticated user
     * @return success/failure to end session
     */
    @PostMapping("/endSession")
    public ResponseEntity<?> endStudySession(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            studySessionService.endStudySession(userDetails.getId());
            return ResponseEntity.ok("Study session ended successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to end study session");
        }
    }

    /**
     * Flips a flashcard during a study session.
     *
     * @param userDetails        Authenticated user details.
     * @param flashcardFlippedDTO DTO containing flashcard flipping information.
     * @return Response indicating success or failure.
     */
    @PostMapping("/flipCard")
    public ResponseEntity<?> flipCard(@AuthenticationPrincipal UserDetailsImpl userDetails, 
                                      @RequestBody FlashcardFlippedDTO flashcardFlippedDTO) 
        {
        try {
            studySessionService.toggleFlashCardFlipped(
                    flashcardFlippedDTO.sessionId(),
                    userDetails.getId(), 
                    flashcardFlippedDTO.studySetId(), 
                    flashcardFlippedDTO.flashcardId()
            );

            return ResponseEntity.ok().body("Success in publishing FlashcardFlippedEvent");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error in publishing FlashcardFlippedEvent");
        }
        }
}
