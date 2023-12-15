package com.activ8.controller;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.activ8.dto.FlashcardDifficultyDTO;
import com.activ8.dto.FlashcardFlippedDTO;
import com.activ8.dto.FlashcardIteratedDTO;
import com.activ8.eventbus.EventBus;
import com.activ8.eventbus.events.FlashcardFlippedEvent;
import com.activ8.eventbus.events.StudySessionProgressEvent;
import com.activ8.eventbus.subscribers.StudySessionProgressEventSubscriber;
import com.activ8.eventbus.subscribers.StudySessionStartedEventSubscriber;
import com.activ8.model.Flashcard;
import com.activ8.model.StudySessionLog;
import com.activ8.service.FlashcardService;
import com.activ8.service.StudySessionLogService;
import com.activ8.service.StudySessionService;
import com.activ8.service.UserDetailsImpl;

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

    // GET REQUESTS

    @GetMapping
    public ResponseEntity<List<StudySessionLog>> getStudySessions(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StudySessionLog> studySessions = studySessionLogService.getAllStudySessionLogs(userDetails.getId());
        return ResponseEntity.ok(studySessions);
    }

    @GetMapping("/{studySessionId}")
    public ResponseEntity<?> getStudySessionById(@PathVariable String studySessionId) {
        return studySessionLogService.getStudySessionLogById(studySessionId)
                .map(studySession -> ResponseEntity.ok().body(studySession))
                .orElse(ResponseEntity.notFound().build());
    }

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
     * Starts a new StudySession
     * 
     * @param userDetails User details of the authenticated user
     * @return The started StudySession
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
     * Updates the difficulty of a flashcard
     * 
     * @param userDetails User details of the authenticated user
     * @return Success/failure
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
