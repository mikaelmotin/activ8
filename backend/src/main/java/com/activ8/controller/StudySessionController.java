package com.activ8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.activ8.dto.FlashcardDifficultyDTO;
import com.activ8.model.Flashcard;
import com.activ8.model.StudySessionLog;
import com.activ8.service.StudySessionService;
import com.activ8.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studysessions")
public class StudySessionController {

    @Autowired
    private StudySessionService studySessionService;

    // GET REQUESTS

    @GetMapping
    public ResponseEntity<List<StudySessionLog>> getStudySessions(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StudySessionLog> studySessions = studySessionService.getAllStudySessionLogs(userDetails.getId());
        return ResponseEntity.ok(studySessions);
    }

    @GetMapping("/{studySessionId}")
    public ResponseEntity<?> getStudySessionById(@PathVariable String studySessionId) {
        return studySessionService.getStudySessionLogById(studySessionId)
                .map(studySession -> ResponseEntity.ok().body(studySession))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nextCard")
    public ResponseEntity<?> nextCard(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Flashcard flashcard = studySessionService.nextCard(userDetails.getId());
            return ResponseEntity.ok().body(flashcard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Couldn't deliver next card");
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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("startSession failed");
        }

        return ResponseEntity.ok("startSession success");
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
            studySessionService.assignDifficultyToFlashcard(
                    userDetails.getId(), 
                    flashcardId,
                    flashcardDifficultyDTO.getDifficultyEnum());
        
        
            return ResponseEntity.ok("assigned difficulty to flashcard successfully");
        
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("assign difficulty to flashcard failed");
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
        studySessionService.endStudySession(userDetails.getId());;
        return ResponseEntity.ok("Study session ended successfully");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok("Failed to end study session");
    }
}




}
